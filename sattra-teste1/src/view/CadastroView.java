package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SalvarController;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CadastroView extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField idadeField;
	private List<Usuario> listadeUsuarios = new ArrayList<Usuario>();
	private JTextField mediaIdadeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroView frame = new CadastroView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(10, 11, 60, 14);
		contentPane.add(nomeLabel);
		
		JLabel idadeLabel = new JLabel("Idade");
		idadeLabel.setBounds(10, 67, 60, 14);
		contentPane.add(idadeLabel);
		
		nomeField = new JTextField();
		nomeLabel.setLabelFor(nomeField);
		nomeField.setBounds(10, 36, 200, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);
		
		idadeField = new JTextField();
		idadeLabel.setLabelFor(idadeField);
		idadeField.setBounds(10, 92, 60, 20);
		contentPane.add(idadeField);
		idadeField.setColumns(10);
		
		JButton cadastroButton = new JButton("Cadastrar");
		cadastroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!"".equals(idadeField.getText()) && !"".equals(nomeField.getText())) { 
					if(validaIdade(idadeField.getText())) {
					Usuario usuario = new Usuario(nomeField.getText(), Integer.parseInt(idadeField.getText()));
					listadeUsuarios.add(usuario);
					nomeField.setText("");
					idadeField.setText("");
				}else {						
						JOptionPane.showMessageDialog(null ,"Insira uma idade valida");
						idadeField.setText("");
				}
				}else {					
					JOptionPane.showMessageDialog(null ,"Preencha os campos Nome e Idade");
				}
			}
		});
		cadastroButton.setBounds(10, 142, 95, 23);
		contentPane.add(cadastroButton);
		
		JButton calcularButton = new JButton("Calcular");
		calcularButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listadeUsuarios.size()>0) {
					float media = 0;
					for (Usuario usuario : listadeUsuarios) {
						media += usuario.getIdade();
					}
					media /= listadeUsuarios.size();
					contentPane.getComponent(7).setVisible(true);
					mediaIdadeField.setText(String.valueOf(media));
					mediaIdadeField.setVisible(true);
				}
			}
		});
		calcularButton.setBounds(132, 142, 95, 23);
		contentPane.add(calcularButton);
		
		JButton salvarButton = new JButton("Salvar");
		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listadeUsuarios.size() > 0) {
					
				String nomearquivo = "";
				nomearquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo no qual deseja salvar\n"
						+ "Caso deixe em branco, sera salvo no arquivo 'Padrao.txt'");
				if("".equals(nomearquivo)) nomearquivo = "Padrao";
				SalvarController.salvarEmTxt(listadeUsuarios, nomearquivo);
				}else {
					JOptionPane.showMessageDialog(null ,"Cadastre ao menos 1 usuario");
				}
			}
		});
		salvarButton.setBounds(10, 176, 95, 23);
		contentPane.add(salvarButton);
		
		JLabel mediaTextLabel = new JLabel("M\u00E9dia das idades");
		mediaTextLabel.setBounds(132, 67, 107, 14);
		contentPane.add(mediaTextLabel);
		contentPane.getComponent(7).setVisible(false);
		
		mediaIdadeField = new JTextField();
		mediaIdadeField.setText("25");
		mediaIdadeField.setHorizontalAlignment(SwingConstants.CENTER);
		mediaIdadeField.setFont(new Font("Tahoma", Font.BOLD, 15));
		mediaIdadeField.setEditable(false);
		mediaIdadeField.setBounds(154, 92, 56, 20);
		contentPane.add(mediaIdadeField);
		mediaIdadeField.setColumns(10);
		
		JButton limpaCadastrosButton = new JButton("Limpar");
		limpaCadastrosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente limpar os usuarios atualmente "
					+ "cadastrados cadastrados?", "Limpar Cadastros", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					listadeUsuarios.clear();
				}				
			}
		});
		limpaCadastrosButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		limpaCadastrosButton.setBounds(132, 176, 95, 23);
		contentPane.add(limpaCadastrosButton);
		mediaIdadeField.setVisible(false);
	}
	
	//Verifica se o campo Idade é um inteiro
	private boolean validaIdade(String idade) {
		try {
			Integer.parseInt(idade);
			return true;			
		}catch(NumberFormatException exception) {
			return false;
		}
		
	}
}
