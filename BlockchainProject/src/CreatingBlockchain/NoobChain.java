package CreatingBlockchain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static final String GENESIS_PREV_HASH = "000000000000000000000000000000000000000000000";
	public static int difficulty = 3;

	public static void main(String[] args) {	
		
		blockchain.add(new Block(0, "transaction 1", GENESIS_PREV_HASH));
		System.out.println("Trying to Mine block 1: ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block(1, "transaction 2",blockchain.get(blockchain.size()-1).hash));
		System.out.println("\n Trying to Mine block 2: ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block(2, "transaction 3",blockchain.get(blockchain.size()-1).hash));
		System.out.println("\n Trying to Mine block 3: ");
		blockchain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);

			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}

			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}

			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
