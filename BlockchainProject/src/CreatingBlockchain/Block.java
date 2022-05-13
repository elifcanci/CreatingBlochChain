package CreatingBlockchain;
import java.util.Date;

public class Block {
	
	private int id;
	private int nonce;
	private long timeStamp;
	public String hash;
	public String previousHash; 
	private String data;

	//Block Constructor.  
	public Block(int id, String data,String previousHash) {
		this.id = id;
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
				String calculatedhash = StringUtil.applySha256( 
				Integer.toString(id) +
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Hash: " + hash +"\n"+ "PreviousHash: " + previousHash);
	}
}
