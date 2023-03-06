package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController 
{
	public KillController()
	{
		super();
	}
	//Codigo para retornar o S.O que está em execução na máquina 
	public String os ()
	{
		String os = System.getProperty("os.name");
		//String arch = System.getProperty("os.arch");
		//String version = System.getProperty("os.version");
		
		String[]vt = os.split(" ");
		if (os.contains("Windows"))
		{
			os = vt[0];
		}else
		{
			if(os.contains("Linux"))
			{
				os = vt[0];
			}
		}
		return os ;
	}
	//Codigo para chamar a lista de processo completa seja para o Windows ou Linux
	public void ListaProcessos()
	{
		//chamando o vetor para saber o SO
		String tasklist = os();
		if(tasklist.contains("Windows"))
		{
			tasklist = "TASKLIST /FO TABLE ";
		}else
			{
				if(tasklist.contains("Linux"))
				{
					tasklist = "ps -ef"; 
				
				}
			}
			try 
			{
				Process p = Runtime.getRuntime().exec(tasklist);
				InputStream fluxo= p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null)
				{
					System.out.println(linha);
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
	}
	//Codigo para encerrar um programa pelo PID seja no Windows ou Linux
	public void MataPID (int pid)
	{
		//chamando o vetor para saber qual o SO
		String sistema = os();
		StringBuffer buffer = new StringBuffer();
		if (sistema.contains("Windows"))
		{
			sistema = "TASKILL /PID";
			
		}else
		 {
			if(sistema.contains("Linux"))
			{
				sistema = "kill -9";
			}
		 }
		try
		{
			buffer.append(sistema);
			buffer.append(" ");
			buffer.append(pid);
			Runtime.getRuntime().exec(buffer.toString());
		}catch(IOException e)
		 {
			String msgErro = e.getMessage();
			if(msgErro.contains("740"))
			{
				StringBuffer bufferzin =  new StringBuffer();
				bufferzin.append("cmd /c " +sistema );
			
				try
				{
				Runtime.getRuntime().exec(bufferzin.toString());
				}catch (IOException e1)
				{
				 e1.printStackTrace();
				}
			}else
			{
				System.err.println(msgErro);
			}
		 }
	}
	
	//Codigo para encerrar um programa pelo nome seja no Windows ou Linux
	public void MataNome(String Nome)
	{
		//Chamando o vetor para saber qual é o SO
		String sistema = os();
		StringBuffer buffer = new StringBuffer();
		
		if(sistema.contains("Windows")) 
		{
			sistema = "TASKKILL /IM";
		} else 
		{
			if(sistema.contains("Linux")) 
			{
				sistema = "pkill -f";
			}
		}
		
		try 
		{
			buffer.append(sistema);
			buffer.append(" ");
		    buffer.append(Nome);
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) 
		  {
				String msgErro = e.getMessage();
				if(msgErro.contains("740")) 
				{
				StringBuffer bufferzin = new StringBuffer();
				bufferzin.append("cmd /c " + sistema);
				try 
				{
					Runtime.getRuntime().exec(bufferzin.toString());
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			} else 
			   {
				System.err.println(msgErro);
			   }
		}
	}
	
	
}


