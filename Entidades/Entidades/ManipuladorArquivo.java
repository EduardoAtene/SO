package Entidades;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ManipuladorArquivo {
	// Leitura do Arquivo
	public static Empilhadeira leitor(String path) throws IOException {
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

		return empilhadeira;
	}
}