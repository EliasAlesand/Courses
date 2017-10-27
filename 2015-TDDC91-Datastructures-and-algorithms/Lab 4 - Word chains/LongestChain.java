
class LongestChain
{
	private Queue q; // k som anvnds i breddenfrstskningen
	private String goalWord; // slutord i breddenfrstskningen
	int wordLength;
	private WordRec currLongest = null;
	public LongestChain(int wordLength) {
		this.wordLength = wordLength;
		q = new Queue();
	}
	private boolean IsGoal(String w)
	{
		return w.equals(goalWord);
	}
	private WordRec MakeSons(WordRec x)
	{
		
		for (String s : WordList.getChildren((x.word))){
			if (WordList.MarkAsUsedIfUnused(s)){
				WordRec wr = new WordRec(s, x);
				if (IsGoal(s)){
					return wr;
				}
				q.Put(wr);
			}
		}
		return null;
	}
	public WordRec BreadthFirst(String startWord, String endWord)
	{
		WordList.EraseUsed();
		WordRec start = new WordRec(startWord, null);
		WordList.MarkAsUsedIfUnused(startWord);
		goalWord = endWord;
		q.Empty();
		q.Put(start);
		try {
			while (true) {
				WordRec wr = MakeSons((WordRec)q.Get());
				if (wr != null) return wr;
			}
		} catch (Exception e) {

			return null;
		}
	}
	private void MakeSonsEnd(WordRec x)
	{
		for (String s : WordList.getChildren((x.word))) {
			if (WordList.MarkAsUsedIfUnused(s)){
				WordRec wr = new WordRec(s, x);
				q.Put(wr);
				if (currLongest == null || wr.ChainLength() > currLongest.ChainLength()){
					currLongest = wr;
				}
			}
		}
	}
	public void BreadthFirst(String searchWord)
	{
		currLongest = null;
		WordList.EraseUsed();
		WordRec start = new WordRec(searchWord, null);
		WordList.MarkAsUsedIfUnused(searchWord);
		q.Empty();
		q.Put(start);
		try {
			while (true) {
				MakeSonsEnd((WordRec)q.Get());	
			}
		} catch (Exception e) {
			System.out.println(searchWord + ": " + currLongest.ChainLength() + " ord");
			if (currLongest != null) {
				currLongest.PrintReverseChain();	
			}
			System.out.println();
		}
	}

}

