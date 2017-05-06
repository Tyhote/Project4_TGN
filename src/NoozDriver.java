
/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class NoozDriver {

	static private NewsDataBaseModel newsDataBaseModel;
	static private SelectionView selectionView;
	static private NewsController newsController;

	public static void main(String[] args) {
		
		//Create the M, V, and C
		newsDataBaseModel = new NewsDataBaseModel();
		selectionView = new SelectionView();
		newsController = new NewsController();
		
		//Make them aware of each other
		newsController.setSelectionView(selectionView);	
		selectionView.setNewsDataBaseModel(newsDataBaseModel);
		newsController.setNewsDataBaseModel(newsDataBaseModel);
			
	}
}
