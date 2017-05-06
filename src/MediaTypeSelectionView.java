import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class MediaTypeSelectionView extends JPanel {
	private static final long serialVersionUID = 1L;

	JCheckBox jcbNewspaper = new JCheckBox(NewsMedia.NEWSPAPER.toString());
	JCheckBox jcbTVNews = new JCheckBox(NewsMedia.TV.toString());
	JCheckBox jcbOnline = new JCheckBox(NewsMedia.ONLINE.toString());

	private JLabel jlblMediaType = new JLabel("Display news stories from which media type(s)?");
	private JButton jbBlank = new JButton();
	JButton jbCancel = new JButton("Cancel");
	JButton jbOkay = new JButton("OK");
	private JPanel jpCompletionButtons = new JPanel(new GridLayout(1, 0));
	private JPanel jpMediaType = new JPanel(new GridLayout(0, 1));

	public MediaTypeSelectionView() {
		this.jbBlank.setVisible(false);
		this.jbOkay.setActionCommand("OK");
		this.jbOkay.setSelected(true);
		this.jbCancel.setActionCommand("Cancel");

		this.jpCompletionButtons.add(this.jbBlank);
		this.jpCompletionButtons.add(this.jbCancel);
		this.jpCompletionButtons.add(this.jbOkay);

		this.jpMediaType.add(this.jlblMediaType);
		this.jpMediaType.add(this.jcbNewspaper);
		this.jpMediaType.add(this.jcbTVNews);
		this.jpMediaType.add(this.jcbOnline);
		this.jpMediaType.add(this.jpCompletionButtons);
		this.jpMediaType.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		this.add(this.jpMediaType);
	}
}