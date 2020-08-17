package alg71898603M.p12;

public class Bucle4 {

	public static long bucle5(int n)
	{
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				for (int k = 1; k <= j; k++)
					for (int l=1; l<=k; l++)
						cont++;
		return cont;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long c = 0;
		long t1, t2;
		int nVeces = Integer.parseInt(args[0]);

		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle5(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(c + "**n=" + n + "**TIEMPO=" + (t2 - t1)
					+ "**nVeces=" + nVeces);

		}
	}
}
