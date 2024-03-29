import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/*
Classe que representa o processamento do cliente como uma thread.
*/
import java.net.Socket;

public class ExemploClienteThread extends Thread {

	private static int jogadas[] = new int [10];
	private static int indiceJogada = 0;
	private Socket mCliente;
	String tLinha;
	private PrintWriter tArq2 = null;
	String usuario;
	
	
	
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	public String gettLinha() {
		return tLinha;
	}

	public void settLinha(String tLinha) {
		this.tLinha = tLinha;
		System.out.println("TLinha thredCli: "+tLinha+"<<<<-----------");
	}

	public PrintWriter gettArq2() {
		return tArq2;
	}

	public void settArq2(PrintWriter tArq2) {
		this.tArq2 = tArq2;
	}

	public ExemploClienteThread(Socket pConexao) {
		mCliente = pConexao;
	}
	
	public Socket getmCliente() {
		return mCliente;
	}

	public void setmCliente(Socket mCliente) {
		this.mCliente = mCliente;
	}

	public void run() {
		
		OutputStream tArq1 = null;
		tArq2 = null;
		InputStream tArq3 = null;
		InputStreamReader tArq4 = null;
		BufferedReader tArq5 = null;

		try {
			System.out.println("Iniciando Thread Cliente " + mCliente.getInetAddress().getHostAddress());
			tArq1 = mCliente.getOutputStream();
			tArq2 = new PrintWriter(tArq1, true);

			tArq3 = mCliente.getInputStream();
			tArq4 = new InputStreamReader(tArq3);
			tArq5 = new BufferedReader(tArq4);

			while (true) {
				tLinha = tArq5.readLine();
				if (tLinha != null) {
					System.out.println(tLinha);
					tArq2.println(tLinha);
					
					
					
					//System.out.println("tarq 2 "+tArq2.toString());
					//System.out.println("view msg outro: "+View.getMsgOutro()+"==================================================*****");
					//View.atualizaTela();
					
					if (tLinha.equalsIgnoreCase("fim"))
						break;
				}
				
			}
			System.out.println("Encerrando Thread Cliente" + mCliente.getInetAddress().getHostAddress());
			//View.atualizaTela(tLinha.toString(), View.getUsuarioUmStr());
			mCliente.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		if (tArq1 != null)
			try {
				tArq1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tArq2 != null)
			tArq2.close();
		if (tArq3 != null)
			try {
				tArq3.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tArq4 != null)
			try {
				tArq4.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tArq5 != null)
			try {
				tArq5.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}

