/*
Classe Servidor
*/

import java.net.*;
import java.io.*;

public class ExemploServidorSocketLendoDadosThread {
	
	
	
	public static void main(String[] args) {
		View v = new View();
		Socket tCliente = null;
		ExemploClienteThread tThreadSrv;
		
		

		try (ServerSocket tServidor = new ServerSocket(3000);) {

			while (true) {
				System.out.println("Servidor com Thread. Esperando uma conex�o...");
				tCliente = tServidor.accept();
				System.out.println("Porta Local: " + tCliente.getLocalPort());
				System.out.println("Porta : " + tCliente.getPort());
				tThreadSrv = new ExemploClienteThread(tCliente);
				
				// As opera��es do cliente ir�o ser executadas em paralelo.
				tThreadSrv.start();
				
				v.atualizaTela(tThreadSrv.gettLinha(), tThreadSrv.getUsuario());
				System.out.println("TThread get linha: -------> "+tThreadSrv.gettLinha()+" <-----------");
				System.out.println("Conex�o estabelecida...");
				System.out.println("Criando uma thread...");

			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		if (tCliente != null)
			try {
				tCliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	

}


