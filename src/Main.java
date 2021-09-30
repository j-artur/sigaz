// import java.util.Scanner;

import java.util.List;
import java.util.Scanner;

import model.dao.*;
import model.vo.*;

public class Main {
	private static Scanner scanner = new Scanner(System.in);

	static void print(Object str) {
		System.out.println(str);
	}

	static String input(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

	public static void main(String[] args) {
		SubjectDAO subjectDAO = new SubjectDAO();

		String command;
		while (true) {
			print("Opções:");
			print("[C] Create");
			print("[R] Read");
			print("[U] Update");
			print("[D] Delete");
			print("[X] Exit");
			command = input("Digite o comando: ");

			switch (command.toUpperCase()) {
				case "C": {
					SubjectVO subject = new SubjectVO();

					print("Criar disciplina");
					subject.setName(input("Nome: "));
					subject.setCode(input("Código: "));
					subjectDAO.create(subject);

					input("Pressione Enter para continuar");
					break;
				}
				case "R": {
					List<SubjectVO> subjects = subjectDAO.getAll();

					print("Disciplinas cadastradas:");
					for (SubjectVO subject : subjects) {
						print(subject);
					}

					input("Pressione Enter para continuar");
					break;
				}
				case "U": {
					SubjectVO subject = new SubjectVO();
					SubjectVO data = new SubjectVO();

					print("Editar disciplina");
					subject.setId(Long.parseLong(input("ID da disciplina a ser editada: ")));
					data.setName(input("Novo nome: "));
					data.setCode(input("Novo código: "));
					subjectDAO.update(subject, data);

					input("Pressione Enter para continuar");
					break;
				}
				case "D": {
					SubjectVO subject = new SubjectVO();

					print("Excluir disciplina");
					subject.setId(Long.parseLong(input("ID da disciplina a ser excluída: ")));
					subjectDAO.delete(subject);

					input("Pressione Enter para continuar");
					break;
				}
				default: {
					return;
				}
			}
		}
	}
}
