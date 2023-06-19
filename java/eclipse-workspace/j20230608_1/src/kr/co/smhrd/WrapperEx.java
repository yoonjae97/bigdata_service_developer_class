package kr.co.smhrd;

public class WrapperEx {

	public static void main(String[] args) {
		// Character ���
		System.out.println(Character.toLowerCase('A')); // 'A'�� �ҹ��ڷ� ��ȯ
		char c1='4', c2='F';
		if(Character.isDigit(c1)) // ���� c1�� �����̸� true
			System.out.println(c1 + "�� ����");
		if(Character.isAlphabetic(c2)) // ���� c2�� �������̸� true
			System.out.println(c2 + "�� ������");
		// Integer ���
		System.out.println(Integer.parseInt("28")); // ���ڿ� "28"�� 10������ ��ȯ
		System.out.println(Integer.toString(28)); // ���� 28�� ���ڿ��� ��ȯ
		System.out.println(Integer.toBinaryString(28)); // 28�� 2���� ���ڿ��� ��ȯ
		System.out.println(Integer.bitCount(28)); // 28�� ���� 2������ 1�� ����
		Integer i = Integer.valueOf(28);
		System.out.println(i.doubleValue()); // ������ double ������ ��ȯ. 28.0
		// Double ���
		Double d = Double.valueOf(3.14);
		System.out.println(d.toString()); // Double�� ���ڿ� "3.14"�� ��ȯ
		System.out.println(Double.parseDouble("3.14")); // ���ڿ��� �Ǽ� 3.14�� ��ȯ
		// Boolean ���
		boolean b = (4>3); // b�� true
		System.out.println(Boolean.toString(b)); // true�� ���ڿ� "true"�� ��ȯ
		System.out.println(Boolean.parseBoolean("false")); // ���ڿ��� false�� ��ȯ


	}

}
