/*
������ �� ��� ���α׷�

�� ���Ϳ� ��ȭ�� ���� ��⿭�� ����ȴ�. 
������ �����Ǳ� ������ �����°� �ȴ�.

�� ��ȭ�� �������� ����ϴ� ��å�� ���� ������� ������ �� �ִ�.
1. ������� ���: ��� ������ ������ ��� �Ǽ��� ó���ϵ��� ������ ��ȭ ������� ���
2. ª�� ��⿭ ã�� ���: �� ��� �ð��� ���̱� ���� ����� ���� �ʴ� �����̳� ���� ª�� ��⿭�� ������ �������� ���
3. �켱������ ���� ���: �� ��޿� ���� ����� ���� ���� ��ȭ�� �켱 ������ ���� �ɷ��� ���� �������� �켱 ����Ѵ�. 

�������̽� : Scheduler 
���� Ŭ���� : RoundRobin(�������),LeastJob(ª�� ��⿭ ����),PriorityAllocation(�켱����)
*/


package com.scheduler;
import java.io.IOException;

public class SchedulerTest {

	public static void main(String[] args)  {  
		System.out.println("��ȭ ��� ��й���� �����ϼ��� ");
		System.out.println("R : �Ѹ� ���ʷ� �Ҵ�");
		System.out.println("L : ���� �ְų� ��Ⱑ ���� ���� �������� �Ҵ�");
		System.out.println("P : �켱������ ���� �� ���� �Ҵ�");
		System.out.print("=====>  ");
		try {	
			int ch = System.in.read();// �Ҵ� ����� �Է¹޾� ch ������ ����
			
			Scheduler scheduler = null;	 // Interface ���� ����		
			if( ch == 'R' || ch == 'r') { 			// �Է¹��� ���� R �Ǵ� r�̸� 
				scheduler = new RoundRobin(); 		// RoundRobinŬ���� ����				
			} else if( ch == 'L' || ch == 'l') { 	// �Է¹��� ���� L �Ǵ� l�̸�
				scheduler = new LeastJob();      	// LeastJob Ŭ���� ����
			} else if (ch == 'P' || ch == 'p') { 	// �Է¹��� ���� P �Ǵ� p�̸�
				scheduler = new PriorityAllocation(); 	// PriorityAllocation Ŭ���� ����
			} else {
				System.out.println("�������� �ʴ� ����Դϴ�.");
				return;
			}
			scheduler.getNextCall();         // � ��å�ΰ� ������� 
			scheduler.sendCallToAgent(); // �������̽��� ������ �޼��� ȣ��		
		}catch(IOException e) {
			System.out.println("���ܹ߻� => "+ e);
	  }
	}
	
}
