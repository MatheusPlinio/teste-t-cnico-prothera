package teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));
		
		funcionarios.removeIf(funcionario -> funcionario.getName().equals("João"));
		
		System.out.println("Funcionarios:");
		for (Funcionario funcionario : funcionarios) {
			System.out.println("Nome: " + funcionario.getName());
			System.out.println("Data de nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Salário: " + formatarValor(funcionario.getSalario()));
			System.out.println("Função: " + funcionario.getFuncao());
			System.out.println();
		}
		
		for (Funcionario funcionario : funcionarios) {
			BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.1"));
			funcionario.setSalario(novoSalario);
		}
		
		Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
		for (Funcionario funcionario : funcionarios) {
			String funcao = funcionario.getFuncao();
			if (!funcionariosPorFuncao.containsKey(funcao)) {
				funcionariosPorFuncao.putIfAbsent(funcao, new ArrayList<>());
				funcionariosPorFuncao.get(funcao).add(funcionario);
			}
			funcionariosPorFuncao.get(funcao).add(funcionario);
		}
		
		System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
		for (Funcionario funcionario : funcionarios) {
			int mesAniversario = funcionario.getDataNascimento().getMonthValue();
			if(mesAniversario == 10 || mesAniversario == 12) {
				System.out.println("Nome: " + funcionario.getName());
				System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
		}
		
		Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Funcionário mais velho:");
        System.out.println("Nome: " + maisVelho.getName());
        System.out.println("Idade: " + idadeMaisVelho);
        
        funcionarios.sort(Comparator.comparing(Funcionario::getName));
        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getName());
        }
        
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos salários: " + formatarValor(totalSalarios));

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários mínimos dos funcionários:");
        for (Funcionario funcionario : funcionarios) {
			BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("Nome: " + funcionario.getName() + " - Salários Mínimos: " + salariosMinimos);
        }
	}
	
	private static String formatarValor(BigDecimal valor) {
        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setGroupingUsed(true);
        return nf.format(valor);
    }
}
