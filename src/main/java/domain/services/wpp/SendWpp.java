package domain.services.wpp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import domain.persona.Persona;
import domain.services.Mensaje;
import domain.services.TipoNotificacion;

public class SendWpp implements TipoNotificacion {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC1f7fa7f69c1884f10d15e5522aa53cf4";
  public static final String AUTH_TOKEN = "b92ecff1c5c9219ca984e1bc0fcafc3c";
  @Override
  public boolean enviarAPersona(Persona persona, String mensaje){
      enviarWpp(persona.getTelefono(),mensaje);
      return true;
  }
  public void enviarWpp(String EnviarA, String mensaje) {
      try {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
              new com.twilio.type.PhoneNumber("whatsapp:"+EnviarA),
              new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
              mensaje)

          .create();

      System.out.println(message.getSid());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
  }

    @Override
    public String toString() {
        return "Whatsapp";
    }

}
