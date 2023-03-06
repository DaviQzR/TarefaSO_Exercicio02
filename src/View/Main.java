package View;
import javax.swing.JOptionPane;

import Controller.KillController; 

public class Main {

	public static void main(String[] args) {
		int opc , pid;
		KillController procController = new KillController();
		String Nome = "";
		pid= 0;
		opc= 0;
		do
		{
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero de acordo com a tabela\n 1- Escrever Lista de tarefas\n 2- Matar um processo por PID\n 3 - Matar um processo pelo nome \n 9- Finaliza "));
			switch(opc)
			{
			case 1: procController.ListaProcessos();
			break;
			
			case 2: pid = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor do PID do processo" ));
			procController.MataPID(pid);
			break;
			
			case 3: Nome = JOptionPane.showInputDialog("Digite o nome do processo para encerrar");
			          procController.MataNome(Nome);
			break;
			
			case 9: JOptionPane.showMessageDialog(null, "Finalizado");
			break;
			
			default: JOptionPane.showMessageDialog(null, "Invalido");
			break;
			}
		}while(opc!=9);			
	}

}
