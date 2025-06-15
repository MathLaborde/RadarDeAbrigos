package br.com.radardeabrigos.service;

import br.com.radardeabrigos.model.Abrigo;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class NotificacaoService {

    private static final Logger logger = Logger.getLogger(NotificacaoService.class.getName());

    public void notificarAbrigoLotado(Abrigo abrigo) {
        // Aqui você implementaria a lógica real de notificação
        // Por exemplo: email, SMS, webhook, etc.

        String mensagem = String.format(
                "ALERTA: Abrigo '%s' em %s/%s está LOTADO! Capacidade: %d pessoas",
                abrigo.getNome(),
                abrigo.getBairro(),
                abrigo.getCidade(),
                abrigo.getCapacidadeTotal()
        );

        logger.warning(mensagem);

        // Simula envio de notificação
        System.out.println("📢 " + mensagem);
    }

    public void notificarNovoAbrigoDisponivel(Abrigo abrigo) {
        String mensagem = String.format(
                "Novo abrigo disponível: '%s' em %s/%s com %d vagas",
                abrigo.getNome(),
                abrigo.getBairro(),
                abrigo.getCidade(),
                abrigo.getVagasDisponiveis()
        );

        logger.info(mensagem);
        System.out.println("✅ " + mensagem);
    }
}
