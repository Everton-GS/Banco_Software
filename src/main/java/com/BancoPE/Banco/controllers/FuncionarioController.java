package com.BancoPE.Banco.controllers;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BancoPE.Banco.entities.ClienteCartaoAuthentication;
import com.BancoPE.Banco.entities.ExtratoCartao;
import com.BancoPE.Banco.entities.Funcionario;
import com.BancoPE.Banco.entities.FuncionarioAuthentication;
import com.BancoPE.Banco.record.BloquearContaRecord;
import com.BancoPE.Banco.record.CancelamentoTransferencia;
import com.BancoPE.Banco.record.DepositoContaRecord;
import com.BancoPE.Banco.record.FuncionarioRegistrarRecord;
import com.BancoPE.Banco.repository.AgenciaRepository;
import com.BancoPE.Banco.repository.ClienteCartaoAuthenticationRepository;
import com.BancoPE.Banco.repository.ExtratoCartaoRepository;
import com.BancoPE.Banco.repository.FuncionarioAuthenticationRepository;
import com.BancoPE.Banco.services.EmailService;
import com.BancoPE.Banco.services.ExtratoCartaoService;
import com.BancoPE.Banco.services.FuncionarioAuthenticationService;
import com.BancoPE.Banco.services.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioAuthenticationService funcionarioAuthenticationService;

    @Autowired
    ClienteCartaoAuthenticationRepository authenticationRepository;

    @Autowired
    AgenciaRepository agenciaRepository;

    @Autowired
    FuncionarioAuthenticationRepository funcionarioAuthenticationRepository;

    @Autowired
    ExtratoCartaoRepository extratoCartaoRepository;

    @Autowired
    ExtratoCartaoService extratoCartaoService;

    @Autowired
    EmailService emailService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid FuncionarioRegistrarRecord funcionarioRecord) {
        try {
            FuncionarioAuthentication funcionarioGerente= (FuncionarioAuthentication)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            FuncionarioAuthentication funcionarioAuthenticationR = funcionarioAuthenticationRepository.findByLogin(funcionarioRecord.cpf());
            
            if (funcionarioGerente.getFuncionario().getAgencia()!=null && funcionarioAuthenticationR==null) {
                Funcionario funcionario = new Funcionario(funcionarioRecord.cargo(),funcionarioGerente.getFuncionario().getAgencia(),funcionarioRecord.cpf(),funcionarioRecord.nome(),funcionarioRecord.genero(),  funcionarioRecord.nascimento(),funcionarioRecord.endereco(),funcionarioRecord.telefone(), funcionarioRecord.email());
                funcionarioService.registrar(funcionario);
                String gerarSenha=funcionarioAuthenticationService.gerarSenha();
                String senhaB=new BCryptPasswordEncoder().encode(gerarSenha);
                FuncionarioAuthentication funcionarioAuthentication = new FuncionarioAuthentication(funcionario, funcionarioRecord.cpf(), senhaB);
                funcionarioAuthenticationService.registrar(funcionarioAuthentication);
              //  emailService.emailAcesso(funcionarioRecord.cpf(),funcionarioRecord.email(), gerarSenha);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/bloquear/cartao")
    public ResponseEntity<?> bloquearConta(@RequestBody BloquearContaRecord bloquear ){
        try {
            ClienteCartaoAuthentication clienteCartaoAuthentication= authenticationRepository.findByCartao(bloquear.cartao());

            if(clienteCartaoAuthentication!=null){
                clienteCartaoAuthentication.setStatus(false);
                funcionarioAuthenticationService.bloquearCartao(clienteCartaoAuthentication);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/estorno")
    public ResponseEntity<?> cancelarTransferencia(@RequestBody CancelamentoTransferencia cancelamentoTransferencia ){
            try {
                Optional<ExtratoCartao> extratoCartao = extratoCartaoRepository.findByID(cancelamentoTransferencia.id());
                if(extratoCartao.isPresent()){
                    extratoCartaoService.estornarValor(extratoCartao.get(),extratoCartao.get().getRemetente(), extratoCartao.get().getDestinatario(),extratoCartao.get().getValor());
                    return ResponseEntity.ok().build();
                }else{
                    return ResponseEntity.badRequest().build();
                }
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
     }
     @Transactional(rollbackOn = Exception.class)
     @PutMapping("/deposito")
     public ResponseEntity<?> depositarCartao(@RequestBody DepositoContaRecord depositoContaRecord ){
        try {
             ClienteCartaoAuthentication cartao = authenticationRepository.findByCartao(depositoContaRecord.cartao());
             if(cartao!=null && depositoContaRecord.valor()>0){
                funcionarioAuthenticationService.depositarCartao(cartao, depositoContaRecord.valor());
                return ResponseEntity.ok().build();
             }else{
                return ResponseEntity.badRequest().build();
             }   
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
     }

    
}
