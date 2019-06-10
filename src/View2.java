import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class View2 extends JFrame implements ActionListener {

	private JFrame principal;
	private JLabel titulo, usuarioUm, usuarioDois, lblIp, lblPorta, lblNomeDeUsuario;
	private static JTextField mensagem;
	private JTextField txtIp;
	private JTextField txtPorta;
	private JTextField txtNomeDeUsuario;
	private static JTextPane conversa;
	private static String usuarioUmStr;
	private String usuarioDoisStr;
	private JButton btnEnviar, btnFim, btnOk;
	private JRadioButton rdUsuario1, rdUsuario2, rdUsuario3, rdUsuario4, rdUsuario5;
	static ExemploClienteSocketLendoDados ecsld;
	static ExemploClienteThread exct;
	private static String msgOutro;
	
	
	
	

	public static String getMsgOutro() {
		return msgOutro;
	}

	public static void setMsgOutro(String msgOutro) {
		View2.msgOutro = msgOutro;
		//atualizaTela(msgOutro);
	}

	public View2() {
		super("View");
		ecsld = new ExemploClienteSocketLendoDados();

		principal = new JFrame("ChatzZz");
		principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		principal.setResizable(false);
		principal.setSize(1200, 680);
		principal.getContentPane().setLayout(null);

		lblIp = new JLabel("IP de conexão: ");
		lblIp.setBounds(10, 70, 180, 20);
		lblIp.setFont(new Font("Arial", Font.BOLD, 18));
		principal.getContentPane().add(lblIp);

		txtIp = new JTextField();
		txtIp.setBounds(195, 70, 200, 20);
		txtIp.setFont(new Font("Arial", Font.BOLD, 18));
		txtIp.setText("127.0.0.1");
		principal.getContentPane().add(txtIp);

		lblPorta = new JLabel("PORTA de conexão: ");
		lblPorta.setBounds(10, 90, 190, 20);
		lblPorta.setFont(new Font("Arial", Font.BOLD, 18));
		principal.getContentPane().add(lblPorta);

		txtPorta = new JTextField();
		txtPorta.setBounds(195, 90, 200, 20);
		txtPorta.setFont(new Font("Arial", Font.BOLD, 18));
		txtPorta.setText("3000");
		principal.getContentPane().add(txtPorta);

		lblNomeDeUsuario = new JLabel("Nome de Usuário:");
		lblNomeDeUsuario.setBounds(10, 50, 160, 20);
		lblNomeDeUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		principal.getContentPane().add(lblNomeDeUsuario);

		txtNomeDeUsuario = new JTextField();
		txtNomeDeUsuario.setBounds(195, 50, 200, 20);
		txtNomeDeUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		principal.getContentPane().add(txtNomeDeUsuario);

		btnOk = new JButton("Ok");
		btnOk.setBounds(10, 110, 100, 20);
		btnOk.addActionListener(this);
		principal.getContentPane().add(btnOk);

		titulo = new JLabel();
		titulo.setText("ChatzZz");
		titulo.setBounds(520, 10, 400, 35);
		titulo.setFont(new Font("Arial", Font.BOLD, 30));
		principal.getContentPane().add(titulo);

		usuarioUm = new JLabel("Usuário: " + usuarioUmStr);
		usuarioUm.setBounds(50, 140, 200, 20);
		usuarioUm.setFont(new Font("Arial", Font.BOLD, 18));
		principal.getContentPane().add(usuarioUm);

		usuarioDois = new JLabel("Usuário: " + usuarioDoisStr);
		usuarioDois.setBounds(890, 140, 200, 20);
		usuarioDois.setFont(new Font("Arial", Font.BOLD, 18));
		// principal.getContentPane().add(usuarioDois);

		conversa = new JTextPane();
		conversa.setBounds(50, 160, 1100, 380);
		conversa.setFont(new Font("Arial", Font.PLAIN, 20));
		conversa.setEditable(false);
		principal.getContentPane().add(conversa);

		mensagem = new JTextField();
		mensagem.setBounds(50, 550, 1100, 40);
		mensagem.setFont(new Font("Arial", Font.PLAIN, 16));
		principal.getContentPane().add(mensagem);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(1030, 595, 120, 30);
		btnEnviar.addActionListener(this);
		principal.getContentPane().add(btnEnviar);

		btnFim = new JButton("Fim");
		btnFim.setBounds(50, 595, 120, 30);
		btnFim.addActionListener(this);
		// principal.getContentPane().add(btnFim);

		principal.setVisible(true);
		//atualizaTela();

	}
	public static void atualizaTela(String tLinha, String usuario) {
		
		System.out.println("Msg Outro: " + tLinha+"????????????????????????????????????????????");
		
		conversa.setText(conversa.getText() + " " + usuario + " : "+ tLinha + "\n");		
		
}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnEnviar) {
			ecsld.setFoiMsg(true);

			if (txtNomeDeUsuario.getText() == null || txtNomeDeUsuario.getText() == ""
					|| txtNomeDeUsuario.getText().isEmpty()) {
				usuarioUmStr = "Anônimo";
				usuarioUm.setText("Usuário: Anônimo");
			} else {
				usuarioUmStr = txtNomeDeUsuario.getText();
				usuarioUm.setText("Usuário: " + txtNomeDeUsuario.getText());
			}
			//setMsgOutro(mensagem.getText().toString());
			
			//setMsgOutro("");
			ecsld.setUsuario(usuarioUmStr.toString());
			ecsld.settLinha(mensagem.getText().toString());
			mensagem.setText("");

			try {
				ecsld.processar();
				ecsld.setFoiMsg(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//conversa.setText(conversa.getText() + " " + usuarioUmStr.toString() + " : " + ecsld.gettLinha().toString()+"\n");

			System.out.println("Debug");
			//atualizaTela(getMsgOutro());

		}

		if (e.getSource() == btnFim) {
			ecsld.settLinha("fim");
			try {
				ecsld.processar();
				ecsld.setFoiMsg(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == btnOk) {

			ecsld.settPorta(Integer.valueOf(txtPorta.getText()));
			ecsld.settHost(txtIp.getText().toString());

			btnOk.setEnabled(false);
			System.out.println("Host: " + txtIp.getText());
			System.out.println("Porta: " + txtPorta.getText());
		}

	}

	

}
