package Entidades;

import java.io.BufferedReader;
import java.io.FileInputStream;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.IOException;
// import java.util.Scanner;
import java.io.InputStreamReader;

public class ManipuladorArquivo {

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
		String linha = "";
		Empilhadeira empilhadeira = new Empilhadeira();
		while (true) {
			if (linha != null) {
				String[] linhaA = null;
				linhaA = linha.split(";");  
				if(linhaA[0] != ""){
					if(linhaA.length == 1){
						empilhadeira.setQuantidadePedidos( Integer.parseInt((linhaA[0])));
					}else{
						empilhadeira.adicionarPedido(linhaA);
					}
				}


			} else
				break;
			linha = buffRead.readLine();
		}

		buffRead.close();
	}

	// public static void escritor(String path) throws IOException {
	// 	BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
	// 	String linha = "";
	// 	Scanner in = new Scanner(System.in);
	// 	System.out.println("Escreva algo: ");
	// 	linha = in.nextLine();
	// 	buffWrite.append(linha + "\n");
	// 	buffWrite.close();
	// }

}