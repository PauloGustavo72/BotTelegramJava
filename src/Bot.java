import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

//acessar o bot father
//crir um novo bot
//pegar o token dado pelo bot father e inserir na linha 27

public class Bot {

	// Criação do objeto bot com as informa��es de acesso
	TelegramBot bot = TelegramBotAdapter.build("456569743:AAGW2yYFJYUUj_rFAya0yQA1dLWSFhT2Uvk");

	// objeto respons�vel por receber as mensagens
	GetUpdatesResponse updatesResponse;
	// objeto respons�vel por gerenciar o envio de respostas
	SendResponse sendResponse;
	// objeto respons�vel por gerenciar o envio de a��es do chat
	BaseResponse baseResponse;
	
	Map<Integer, Location> locais = new HashMap<Integer, Location>();

	public void run() throws Exception {
		// controle de off-set, isto �, a partir deste ID ser� lido as mensagens
		// pendentes na fila

		int m = 0;
		
		ReplyKeyboardMarkup keyboardLocation = new ReplyKeyboardMarkup(
		        new KeyboardButton[]{
		                new KeyboardButton("Informar localização").requestLocation(true)
		        }
		);
		
		//keyboardLocation.oneTimeKeyboard(true);
		keyboardLocation.resizeKeyboard(true);
		
		// loop infinito pode ser alterado por algum timer de intervalo curto
		while (true) {
			GetUpdates getUpdates = new GetUpdates().limit(100).offset(m).timeout(0);

			// executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial)
			//updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));
			updatesResponse = bot.execute(getUpdates);
			
			// lista de mensagens
			List<Update> updates = updatesResponse.updates();
			
			//for(Update update: updates) {
				//System.out.println(message.location());
			//}
			
			// an�lise de cada a��o da mensagem
			for (Update update : updates) {
				
				Message message = update.message();

				// atualiza��o do off-setupdatesResponse = bot.execute(new GetUpdates().l)

				m = update.updateId() + 1;
				System.out.println("Offset: " + m);
				
				System.out.println("Chat ID: " + message.chat().id());
				System.out.println("User ID: " + message.from().id());
				

				if(message.text() == null) {
					locais.put(message.from().id(), message.location());
					continue;
				}
				
				if(locais.get(message.from().id()) == null) {
					sendResponse = bot.execute(new SendMessage(message.chat().id(), "Por favor informe sua localização.").replyMarkup(keyboardLocation));
					continue;
				}
				String latitude = "" + locais.get(message.from().id()).latitude();
				String longitude = "" + locais.get(message.from().id()).longitude();
				
				
				
				System.out.println("Localização: " + "Latidude = " + latitude  + ", Longiture = " + longitude); 
				System.out.println("Recebendo mensagem: " + message.text());

				// envio de "Escrevendo" antes de enviar a resposta
				baseResponse = bot.execute(new SendChatAction(message.chat().id(), ChatAction.typing.name()));

				String texto = message.text();
				if (texto.equals("/ajuda")) {
					sendResponse = bot.execute(new SendMessage(message.chat().id(), "Digite o que deseja pesquisar."));
					locais.remove(message.from().id());
				} else {

					HttpURLConnectionExample http = new HttpURLConnectionExample();

					// verifica��o de a��o de chat foi enviada com sucesso
					if(baseResponse.isOk())
						System.out.println("Resposta de Chat Action enviada!");
					// envio da mensagem de resposta
					sendResponse = bot.execute(new SendMessage(message.chat().id(), http.sendGet(texto, latitude, longitude).toString()));

					// verifica��o de mensagem enviada com sucesso
					if(sendResponse.isOk())
						System.out.println("Mensagem enviada!");
					
				}
			
				

			}

		}

	}

}