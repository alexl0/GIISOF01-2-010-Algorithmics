package alg71898603.p4;

public class HuffmanTiempos {
	private Generator generator;
	private Huffman huffman;
	
	public HuffmanTiempos() {
		generator=new Generator();
	}
	public void run() {
		for(int i=4000;i<Integer.MAX_VALUE;i*=2) {
			generator.run("test.txt", i, 20, 5, 1000, 1);
			huffman=new Huffman("test.txt");
			long t1=System.currentTimeMillis();
			huffman.run();
			long t2=System.currentTimeMillis();
			System.out.println(i+"\t"+(t2-t1));
		}
	}
	public static void main(String[]args) {
		HuffmanTiempos h=new HuffmanTiempos();
		h.run();
	}
}
