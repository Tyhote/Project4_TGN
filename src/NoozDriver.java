
public class NoozDriver {

	static private NewsDataBaseModel newsDataBaseModel;
	static private SelectionView selectionView;
	static private NewsController newsController;

	public static void main(String[] args) {
		
		//Create the M, V, and C
		newsDataBaseModel = new NewsDataBaseModel();
		selectionView = new SelectionView();
		newsController = new NewsController();
		newsController.setSelectionView(selectionView);	
		
		
		//Make them aware of each other
		selectionView.setNewsDataBaseModel(newsDataBaseModel);
		newsController.setNewsDataBaseModel(newsDataBaseModel);
			
	}
}
