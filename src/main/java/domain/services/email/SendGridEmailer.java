package domain.services.email;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import domain.persona.Persona;
import domain.services.Mensaje;
import domain.services.TipoNotificacion;

import java.io.IOException;

public class SendGridEmailer implements TipoNotificacion {
  @Override
  public boolean enviarAPersona(Persona persona, String mensaje){
    enviarEmail(persona.getMail(),mensaje);
    return false;
  }
  public void enviarEmail(String EnviarA, String mensaje)  {
    try{ 
      Email from = new Email("jpolito@frba.utn.edu.ar");// use your own email address here
      Email to = new Email(EnviarA);

      String subject = "Incidente";
      Content content = new Content("text/html", mensaje);

      Mail mail = new Mail(from, subject, to, content);
      //SENDGRID_API_KEY=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
      //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
      SendGrid sg = new SendGrid("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");
      Request request = new Request();

      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);

      System.out.println(response.getStatusCode());
      System.out.println(response.getHeaders());
      System.out.println(response.getBody());

      } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public String toString() {
    return "Mail";
  }
}
