
import java.net.*;
import java.util.Scanner;
import java.io.*;

/*
 * Para executar devemos:
 * Executar o servidor no Eclipse: Run As na classe ExemploServidorSocketLendoDados
 *               
em seguida abrir o ambiente da console, digitando cmd em executar no windows.
Posicionar-se no nível do pacote e digitar o comando:
D:\workspace\livroClienteServidor>java -cp . modulo18.ExemploServidorClienteLend
oDados
Digite um host : 127.0.0.1
Digite o n·mero da Porta(3000):
3000
ConexÒo estabelecida.
 Digite um texto:
ola senhor servidor tudo bem
Enviando...|ola senhor servidor tudo bem|
Recebendo..|ola senhor servidor tudo bem|
 Digite um texto:
 
 Analisar no ambiente Eclipse e no emulador da console as respostas recebidas.

 */
public class ExemploClienteSocketLendoDados {
	public static void main(String[] args) throws IOException {
		new ExemploClienteSocketLendoDados().processar();
		
	}
	
	Scanner sc = new Scanner(System.in).useDelimiter("\r\n");
	String tHost;
	int tPorta;
	String tLinha;
	boolean foiMsg = false;
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
	}


	public String gettHost() {
		return tHost;
	}


	public void settHost(String tHost) {
		this.tHost = tHost;
	}


	public int gettPorta() {
		return tPorta;
	}


	public void settPorta(int tPorta) {
		this.tPorta = tPorta;
	}
	

	public boolean isFoiMsg() {
		return foiMsg;
	}


	public void setFoiMsg(boolean foiMsg) {
		this.foiMsg = foiMsg;
	}


	public void processar() throws IOException {
		

		OutputStream tArq1 = null;
		PrintWriter tArq2 = null;
		InputStream tArq3 = null;
		InputStreamReader tArq4 = null;
		BufferedReader tArq5 = null;
		

		while (true) {
			System.out.print("Digite um host : ");
			//tHost = sc.next();
			tHost = this.gettHost();
			
			//System.out.println("Ip: "+tHost);
			if (tHost.equals("fim"))
				break;

			System.out.println("Digite o número da Porta(3000): ");
			//tPorta = sc.nextInt();
			tPorta = this.gettPorta();
			System.out.println("tPorta: "+tPorta);
			try (Socket tSocket = new Socket(tHost, tPorta);) {

				System.out.println("Conexão estabelecida.");

				tArq1 = tSocket.getOutputStream();
				//System.out.println("tArq1: "+tArq1.toString());
				tArq2 = new PrintWriter(tArq1, true);
				//System.out.println("tArq2: "+tArq2.toString());
				tArq3 = tSocket.getInputStream();
				//System.out.println("tArq3: "+tArq3.toString());
				tArq4 = new InputStreamReader(tArq3);
				//System.out.println("tArq4: "+tArq4.toString());
				tArq5 = new BufferedReader(tArq4);
				//System.out.println("tArq5: "+tArq5.toString());

				while (foiMsg == true) {
					System.out.println(" Digite um texto: ");
					//tLinha = sc.next();
					tLinha = this.gettLinha();
					if (tLinha.equals("fim") || tLinha.equals("quit")) {
						tArq2.println(tLinha);
						break;
					}

					System.out.println("Enviando...|" + tLinha + "|");
					tArq2.println(tLinha);

					tLinha = tArq5.readLine();
					System.out.println("Recebendo..|" + tLinha + "|");
					this.setFoiMsg(false);
					//v.atualizaTela(tLinha, getUsuario());
					//View2.atualizaTela(tLinha, getUsuario());
					
					//System.out.println("Foi Msg: "+this.isFoiMsg()+"----------------------------------------------------------------------------");
				}
				tArq2.close();
				tArq5.close();
				tSocket.close();
			} catch (UnknownHostException e) {
				System.out.println("IP não encontrado.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erro na conexão.");
				e.printStackTrace();
			}
			break;
		}
		
		

		if (tArq1 != null)
			tArq1.close();
		if (tArq2 != null)
			tArq2.close();
		if (tArq3 != null)
			tArq3.close();
		if (tArq4 != null)
			tArq4.close();
		if (tArq5 != null)
			tArq5.close();

	}
}


