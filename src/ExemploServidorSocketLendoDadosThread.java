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
				System.out.println("Servidor com Thread. Esperando uma conexão...");
				tCliente = tServidor.accept();
				System.out.println("Porta Local: " + tCliente.getLocalPort());
				System.out.println("Porta : " + tCliente.getPort());
				tThreadSrv = new ExemploClienteThread(tCliente);
				
				// As operações do cliente irão ser executadas em paralelo.
				tThreadSrv.start();
				
				v.atualizaTela(tThreadSrv.gettLinha(), tThreadSrv.getUsuario());
				System.out.println("TThread get linha: -------> "+tThreadSrv.gettLinha()+" <-----------");
				System.out.println("Conexão estabelecida...");
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


