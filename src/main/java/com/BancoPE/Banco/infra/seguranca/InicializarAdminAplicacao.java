package com.BancoPE.Banco.infra.seguranca;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.BancoPE.Banco.entities.AcessoRole;
import com.BancoPE.Banco.entities.Agencia;
import com.BancoPE.Banco.entities.Cliente;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.entities.GeneroRole;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.ClienteRepository;
import com.BancoPE.Banco.repository.FuncionarioRepository;
import com.BancoPE.Banco.services.FuncionarioAuthenticationService;

@Component
public class InicializarAdminAplicacao implements CommandLineRunner {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    FuncionarioAuthenticationService authenticationService;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteCartaoAuthenticationRepository cartaoAuthenticationRepository;

    @Override
    public void run(String... args) throws Exception {
        cadastrarAgencia();
        cadastrarFuncionarioMaster();
        cadastroCliente();
    }
    private void cadastrarAgencia() {
        Optional<Agencia> agencia = agenciaRepository.findByAgenciaNumero("0001");
        if (agencia.isEmpty()) {
            Agencia agenciaCriar = new Agencia("0001", "Rua do Sol", "3897-3978");
            agenciaRepository.save(agenciaCriar);
        }
    }
    private void cadastrarFuncionarioMaster(){
        Optional<Agencia> agencia = agenciaRepository.findByAgenciaNumero("0001");
        Optional<Funcionario> funcionario = funcionarioRepository.findBycpf("692.812.590-01");
        if (funcionario.isEmpty()) {
            Funcionario funcionarioGerente = new Funcionario(AcessoRole.Gerente,agencia.get(),"692.812.590-01",
                    "Everton", GeneroRole.Masculino, LocalDate.of(2000, 04, 10), "Rua do sol", "4002-8922", "Banco@gmail.com");
            funcionarioRepository.save(funcionarioGerente);
            String senha="$2a$10$.Lu6FVbCB8btR8Wpeiyjo.HY/3I2lyb/NGaweKQSknR0RLharTNsC";

            FuncionarioAuthentication funcionarioAuthentication = new FuncionarioAuthentication(funcionarioGerente,"692.812.590-01" , senha);
            authenticationService.registrar(funcionarioAuthentication);
        }
    }
    private void cadastroCliente(){
        Optional<Agencia> agencia = agenciaRepository.findByAgenciaNumero("0001");
        Optional<Cliente> cliente= clienteRepository.findByCpf("137.329.610-01");
        if(cliente.isEmpty() && agencia.isPresent()){
            Cliente clienteN = new Cliente("Everton", "137.329.610-01", GeneroRole.Masculino, LocalDate.of(2000,04,10), "Rua 15", "4002-8922", "everton@gmail.com",AcessoRole.Cliente);
            clienteRepository.save(clienteN);
            LocalDate dataVencimento = LocalDate.now().plusYears(4);
            String senha ="$2a$10$fl2CYgbw/xqEe/qPhkpTpuPydU5Svvaljflt51zEsGSEL6nt7A7de";
            ClienteCartaoAuthentication cartaoAuthentication = new ClienteCartaoAuthentication("0001-000001",senha,clienteN,0,dataVencimento);
            cartaoAuthenticationRepository.save(cartaoAuthentication);

        }
    }

}
