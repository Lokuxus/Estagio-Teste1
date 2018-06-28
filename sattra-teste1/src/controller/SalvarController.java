package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import model.Usuario;

public class SalvarController {

	
	public static void salvarEmTxt(List<Usuario> listadeUsuarios, String arquivoNome) {
		try {
		File arquivo = new File("Arquivos Salvos/"+ arquivoNome + ".txt");
		if(!arquivo.exists()) arquivo.createNewFile();
		FileWriter fileWriter = new FileWriter(arquivo, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
		printWriter.println("/*-----------------------------------------------Nova Execução----------------------------------------------------------*/");
		printWriter.println("Nome \t\tIdade");
		for (Usuario usuario : listadeUsuarios) {
			printWriter.println(usuario.getNome()+"\t\t"+usuario.getIdade());
		}
		printWriter.close();
		bufferedWriter.close();
		fileWriter.close();
		}catch(Exception exception) {
			exception.printStackTrace();
			
		}
	}
}
