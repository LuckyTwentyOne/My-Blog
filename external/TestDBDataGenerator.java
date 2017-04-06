import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * http://www.blindtextgenerator.com/lorem-ipsum - text generator: Lorem ipsum
 * ,1003 words https://placeimg.com/1000/400/tech - image generator with content
 * http://names.mongabay.com/male_names.htm - English names http://uifaces.com/
 * - User avatar generator
 * 
 * Please add postgres JDBC driver to classpath before launch this generator
 * 
 */
public class TestDBDataGenerator {
	// Test sentences for content generation
	private static final String DUMMY_CONTENT_TEXT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut eros et nisl sagittis vestibulum. Nullam nulla eros, ultricies sit amet, nonummy id, imperdiet feugiat, pede. Sed lectus. Donec mollis hendrerit risus. Phasellus nec sem in justo pellentesque facilisis. Etiam imperdiet imperdiet orci. Nunc nec neque. Phasellus leo dolor, tempus non, auctor et, hendrerit quis, nisi. Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor congue, elit erat euismod orci, ac placerat dolor lectus quis orci. Phasellus consectetuer vestibulum elit. Aenean tellus metus, bibendum sed, posuere ac, mattis non, nunc. Vestibulum fringilla pede sit amet augue. In turpis. Pellentesque posuere. Praesent turpis. Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc, eu sollicitudin urna dolor sagittis lacus. Donec elit libero, sodales nec, volutpat a, suscipit non, turpis. Nullam sagittis. Suspendisse pulvinar, augue ac venenatis condimentum, sem libero volutpat nibh, nec pellentesque velit pede quis nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce id purus. Ut varius tincidunt libero. Phasellus dolor. Maecenas vestibulum mollis diam. Pellentesque ut neque. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In dui magna, posuere eget, vestibulum et, tempor auctor, justo. In ac felis quis tortor malesuada pretium. Pellentesque auctor neque nec urna. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Aenean viverra rhoncus pede. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut non enim eleifend felis pretium feugiat. Vivamus quis mi. Phasellus a est. Phasellus magna. In hac habitasse platea dictumst. Curabitur at lacus ac velit ornare lobortis. Curabitur a felis in nunc fringilla tristique. Morbi mattis ullamcorper velit. Phasellus gravida semper nisi. Nullam vel sem. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Sed hendrerit. Morbi ac felis. Nunc egestas, augue at pellentesque laoreet, felis eros vehicula leo, at malesuada velit leo quis pede. Donec interdum, metus et hendrerit aliquet, dolor diam sagittis ligula, eget egestas libero turpis vel mi. Nunc nulla. Fusce risus nisl, viverra et, tempor et, pretium in, sapien. Donec venenatis vulputate lorem. Morbi nec metus. Phasellus blandit leo ut odio. Maecenas ullamcorper, dui et placerat feugiat, eros pede varius nisi, condimentum viverra felis nunc et lorem. Sed magna purus, fermentum eu, tincidunt eu, varius ut, felis. In auctor lobortis lacus. Quisque libero metus, condimentum nec, tempor a, commodo mollis, magna. Vestibulum ullamcorper mauris at ligula. Fusce fermentum. Nullam cursus lacinia erat. Praesent blandit laoreet nibh. Fusce convallis metus id felis luctus adipiscing. Pellentesque egestas, neque sit amet convallis pulvinar, justo nulla eleifend augue, ac auctor orci leo non est. Quisque id mi. Ut tincidunt tincidunt erat. Etiam feugiat lorem non metus. Vestibulum dapibus nunc ac augue. Curabitur vestibulum aliquam leo. Praesent egestas neque eu enim. In hac habitasse platea dictumst. Fusce a quam. Etiam ut purus mattis mauris sodales aliquam. Curabitur nisi. Quisque malesuada placerat nisl. Nam ipsum risus, rutrum vitae, vestibulum eu, molestie vel, lacus. Sed augue ipsum, egestas nec, vestibulum et, malesuada adipiscing, dui. Vestibulum facilisis, purus nec pulvinar iaculis, ligula mi congue nunc, vitae euismod ligula urna in dolor. Mauris sollicitudin fermentum libero. Praesent nonummy mi in odio. Nunc interdum lacus sit amet orci. Vestibulum rutrum, mi nec elementum vehicula, eros quam gravida nisl, id fringilla neque ante vel mi. Morbi mollis tellus ac sapien. Phasellus volutpat, metus eget egestas mollis, lacus lacus blandit dui, id egestas quam mauris ut lacus. Fusce vel dui. Sed in libero ut nibh placerat accumsan. Proin faucibus arcu quis ante. In consectetuer turpis ut velit. Nulla sit amet est. Praesent metus tellus, elementum eu, semper a, adipiscing nec, purus. Cras risus ipsum, faucibus ut, ullamcorper id, varius ac, leo. Suspendisse feugiat. Suspendisse enim turpis, dictum sed, iaculis a, condimentum nec, nisi. Praesent nec nisl a purus blandit viverra. Praesent ac massa at ligula laoreet iaculis. Nulla neque dolor, sagittis eget, iaculis quis, molestie non, velit. Mauris turpis nunc, blandit et, volutpat molestie, porta ut, ligula. Fusce pharetra convallis urna. Quisque ut nisi. Donec mi odio, faucibus at, scelerisque quis, convallis in, nisi. ";
	// English male name list
	private static final String NAMES_LIST = "James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donald,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger,Joe,Juan,Jack,Albert,Jonathan,Justin,Terry,Gerald,Keith,Samuel,Willie,Ralph,Lawrence,Nicholas,Roy,Benjamin,Bruce,Brandon,Adam,Harry,Fred,Wayne,Billy,Steve,Louis,Jeremy,Aaron,Randy,Howard,Eugene,Carlos,Russell,Bobby,Victor,Martin,Ernest,Phillip,Todd,Jesse,Craig,Alan,Shawn,Clarence,Sean,Philip,Chris,Johnny,Earl,Jimmy,Antonio,Danny,Bryan,Tony,Luis,Mike,Stanley,Leonard,Nathan,Dale,Manuel,Rodney,Curtis,Norman,Allen,Marvin,Vincent,Glenn,Jeffery,Travis,Jeff,Chad,Jacob,Lee,Melvin,Alfred,Kyle,Francis,Bradley,Jesus,Herbert,Frederick,Ray,Joel,Edwin,Don,Eddie,Ricky,Troy,Randall,Barry,Alexander,Bernard,Mario,Leroy,Francisco,Marcus,Micheal,Theodore,Clifford,Miguel,Oscar,Jay,Jim,Tom,Calvin,Alex,Jon,Ronnie,Bill,Lloyd,Tommy,Leon,Derek,Warren,Darrell,Jerome,Floyd,Leo,Alvin,Tim,Wesley,Gordon,Dean,Greg,Jorge,Dustin,Pedro,Derrick,Dan,Lewis,Zachary,Corey,Herman,Maurice,Vernon,Roberto,Clyde,Glen,Hector,Shane,Ricardo,Sam,Rick,Lester,Brent,Ramon,Charlie,Tyler,Gilbert,Gene,Marc,Reginald,Ruben,Brett,Angel,Nathaniel,Rafael,Leslie,Edgar,Milton,Raul,Ben,Chester,Cecil,Duane,Franklin,Andre,Elmer,Brad,Gabriel,Ron,Mitchell,Roland,Arnold,Harvey,Jared,Adrian,Karl,Cory,Claude,Erik,Darryl,Jamie,Neil,Jessie,Christian,Javier,Fernando,Clinton,Ted,Mathew,Tyrone,Darren,Lonnie,Lance,Cody,Julio,Kelly,Kurt,Allan,Nelson,Guy,Clayton,Hugh,Max,Dwayne,Dwight,Armando,Felix,Jimmie,Everett,Jordan,Ian,Wallace,Ken,Bob,Jaime,Casey,Alfredo,Alberto,Dave,Ivan,Johnnie,Sidney,Byron,Julian,Isaac,Morris,Clifton,Willard,Daryl,Ross,Virgil,Andy,Marshall,Salvador,Perry,Kirk,Sergio,Marion,Tracy,Seth,Kent,Terrance,Rene,Eduardo,Terrence,Enrique,Freddie,Wade,Austin,Stuart,Fredrick,Arturo,Alejandro,Jackie,Joey,Nick,Luther,Wendell,Jeremiah,Evan,Julius,Dana,Donnie,Otis,Shannon,Trevor,Oliver,Luke,Homer,Gerard,Doug,Kenny,Hubert,Angelo,Shaun,Lyle,Matt,Lynn,Alfonso,Orlando,Rex,Carlton,Ernesto,Cameron,Neal,Pablo,Lorenzo,Omar,Wilbur,Blake,Grant,Horace,Roderick,Kerry,Abraham,Willis,Rickey,Jean,Ira,Andres,Cesar,Johnathan,Malcolm,Rudolph,Damon,Kelvin,Rudy,Preston,Alton,Archie,Marco,Wm,Pete,Randolph,Garry,Geoffrey,Jonathon,Felipe,Bennie,Gerardo,Ed,Dominic,Robin,Loren,Delbert,Colin,Guillermo,Earnest,Lucas,Benny,Noel,Spencer,Rodolfo,Myron,Edmund,Garrett,Salvatore,Cedric,Lowell,Gregg,Sherman,Wilson,Devin,Sylvester,Kim,Roosevelt,Israel,Jermaine,Forrest,Wilbert,Leland,Simon,Guadalupe,Clark,Irving,Carroll,Bryant,Owen,Rufus,Woodrow,Sammy,Kristopher,Mack,Levi,Marcos,Gustavo,Jake,Lionel,Marty,Taylor,Ellis,Dallas,Gilberto,Clint,Nicolas,Laurence,Ismael,Orville,Drew,Jody,Ervin,Dewey,Al,Wilfred,Josh,Hugo,Ignacio,Caleb,Tomas,Sheldon,Erick,Frankie,Stewart,Doyle,Darrel,Rogelio,Terence,Santiago,Alonzo,Elias,Bert,Elbert,Ramiro,Conrad,Pat,Noah,Grady,Phil,Cornelius,Lamar,Rolando,Clay,Percy,Dexter,Bradford,Merle,Darin,Amos,Terrell,Moses,Irvin,Saul,Roman,Darnell,Randal,Tommie,Timmy,Darrin,Winston,Brendan,Toby,Van,Abel,Dominick,Boyd,Courtney,Jan,Emilio,Elijah,Cary,Domingo,Santos,Aubrey,Emmett,Marlon,Emanuel,Jerald,Edmond,Emil,Dewayne,Will,Otto,Teddy,Reynaldo,Bret,Morgan,Jess,Trent,Humberto,Emmanuel,Stephan,Louie,Vicente,Lamont,Stacy,Garland,Miles,Micah,Efrain,Billie,Logan,Heath,Rodger,Harley,Demetrius,Ethan,Eldon,Rocky,Pierre,Junior,Freddy,Eli,Bryce,Antoine,Robbie,Kendall,Royce,Sterling,Mickey,Chase,Grover,Elton,Cleveland,Dylan,Chuck,Damian,Reuben,Stan,August,Leonardo,Jasper,Russel,Erwin,Benito,Hans,Monte,Blaine,Ernie,Curt,Quentin,Agustin,Murray,Jamal,Devon,Adolfo,Harrison,Tyson,Burton,Brady,Elliott,Wilfredo,Bart,Jarrod,Vance,Denis,Damien,Joaquin,Harlan,Desmond,Elliot,Darwin,Ashley,Gregorio,Buddy,Xavier,Kermit,Roscoe,Esteban,Anton,Solomon,Scotty,Norbert,Elvin,Williams,Nolan,Carey,Rod,Quinton,Hal,Brain,Rob,Elwood,Kendrick,Darius,Moises,Son,Marlin,Fidel,Thaddeus,Cliff,Marcel,Ali,Jackson,Raphael,Bryon,Armand,Alvaro,Jeffry,Dane,Joesph,Thurman,Ned,Sammie,Rusty,Michel,Monty,Rory,Fabian,Reggie,Mason,Graham,Kris,Isaiah,Vaughn,Gus,Avery,Loyd,Diego,Alexis,Adolph,Norris,Millard,Rocco,Gonzalo,Derick,Rodrigo,Gerry,Stacey,Carmen,Wiley,Rigoberto,Alphonso,Ty,Shelby,Rickie,Noe,Vern,Bobbie,Reed,Jefferson,Elvis,Bernardo,Mauricio,Hiram,Donovan,Basil,Riley,Ollie,Nickolas,Maynard,Scot,Vince,Quincy,Eddy,Sebastian,Federico,Ulysses,Heriberto,Donnell,Cole,Denny,Davis,Gavin,Emery,Ward,Romeo,Jayson,Dion,Dante,Clement,Coy,Odell,Maxwell,Jarvis,Bruno,Issac,Mary,Dudley";
	// How many categories should be created
	private static final int CATEGORY_SIZE = 10;
	// What min articles per category should be generated
	private static final int MIN_ARTICLES_PER_CATEGORY = 1;
	// What max articles per category should be generated
	private static final int MAX_ARTICLES_PER_CATEGORY = 30;
	// Min article id
	private static final int START_ARTICLE_ID = 200;
	// Absolute path to media directory for blog project
	private static final String DEST_MEDIA = "C:\\Users\\ASUS\\workspace\\MyBlog\\src\\main\\webapp\\media";
	// JDBC setting for blog database
	private static final String JDBC_URL = "jdbc:postgresql://localhost/blog";
	private static final String JDBC_USERNAME = "blog";
	private static final String JDBC_PASSWORD = "password";

	// Others variables and method are for internal use only
	/*****************************************************************************************************************************/
	// For https://placeimg.com
	private static final String[] IMG_THEME = { "arch", "nature", "tech", "animals" };
	private static final String[] NAMES = NAMES_LIST.split(",");
	private static final List<String> SENTENCES = new ArrayList<>();
	private static final List<String> WORDS = new ArrayList<>();
	private static final Random r = new Random();
	private static final long MILISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
	private static int idArticle = START_ARTICLE_ID;
	private static int articleCount = 0;
	private static int accountCount = 0;

	private static void init() {
		String[] sentences = DUMMY_CONTENT_TEXT.split("\\.");
		for (String sentence : sentences) {
			sentence = sentence.trim();
			if (sentence.length() > 0) {
				SENTENCES.add(sentence + ".");
			}
		}
		String[] words = DUMMY_CONTENT_TEXT.split(" ");
		for (String word : words) {
			word = word.replace(",", "").replace(".", "").replace(";", "").trim().toLowerCase();
			if (!WORDS.contains(word) && word.length() >= 4) {
				WORDS.add(word);
			}
		}
		Collections.shuffle(SENTENCES);
		Collections.shuffle(WORDS);
		System.out.println("SENTENCES SIZE=" + SENTENCES.size());
		System.out.println("WORDS SIZE=" + WORDS.size());
	}

	private static void clearDb(Connection c) throws SQLException {
		try (Statement st = c.createStatement()) {
			st.executeUpdate("delete from comment");
			st.executeUpdate("delete from article");
			st.executeUpdate("delete from account");
			st.executeUpdate("delete from category");
			st.executeQuery("select setval('account_seq', 1, false)");
			st.executeQuery("select setval('comment_seq', 1, false)");
		}
		c.commit();
		System.out.println("Db data deleted");
	}

	private static int generateArticleCount() {
		int diff = r.nextInt(3);
		switch (diff) {
		case 0: {
			return r.nextInt(2 * MIN_ARTICLES_PER_CATEGORY) + MIN_ARTICLES_PER_CATEGORY;
		}
		case 1: {
			return r.nextInt(MAX_ARTICLES_PER_CATEGORY / 2) + MIN_ARTICLES_PER_CATEGORY;
		}
		case 2: {
			return r.nextInt(MAX_ARTICLES_PER_CATEGORY) + MAX_ARTICLES_PER_CATEGORY / 2;
		}
		}
		throw new IllegalStateException("This exception is not possible");
	}

	private static List<CategoryItem> generateCategories(Connection c) throws SQLException {
		List<CategoryItem> categories = new ArrayList<>();
		try (PreparedStatement ps = c.prepareStatement("insert into category values (?,?,?,?)")) {
			for (int i = 0; i < CATEGORY_SIZE; i++) {
				String url = WORDS.get(i);
				String name = Character.toUpperCase(url.charAt(0)) + url.substring(1);
				int articles = generateArticleCount();
				ps.setInt(1, i + 1);
				ps.setString(2, name);
				ps.setString(3, "/" + url);
				ps.setInt(4, articles);
				ps.addBatch();
				categories.add(new CategoryItem(i + 1, articles));
				articleCount += articles;
			}
			ps.executeBatch();
			c.commit();
		}
		System.out.println("Categories created: " + categories.size());
		return categories;
	}

	private static String createArticleUrl(String title) {
		return "/" + title.replace(" ", "-").replace(".", "").replace(",", "").toLowerCase().trim();
	}

	private static String generateArticleMainImageLink(String theme) throws IOException {
		String uid = UUID.randomUUID().toString() + ".jpg";
		String fileName = DEST_MEDIA + "\\" + theme + "\\" + uid;
		downloadImageFromInternet("https://placeimg.com/1000/400/" + theme, fileName);
		return "/MyBlog/media/" + theme + "/" + uid;
	}

	private static String generateAccountAvatar() throws IOException {
		String uid = UUID.randomUUID().toString() + ".jpg";
		String fileName = DEST_MEDIA + "\\avatar\\" + uid;
		downloadImageFromInternet("https://placeimg.com/80/80/people", fileName);
		return "/MyBlog/media/avatar/" + uid;
	}

	private static String generateContentImageHtml(String theme) throws IOException {
		int w = r.nextInt(5) * 100 + 200;
		int h = r.nextInt(10) * 20 + 100;
		String uid = UUID.randomUUID().toString() + ".jpg";
		String fileName = DEST_MEDIA + "\\" + theme + "\\" + uid;
		downloadImageFromInternet("https://placeimg.com/" + w + "/" + h + "/" + theme, fileName);
		return "<img class=\"float-center\" src=\"/MyBlog/media/" + theme + "/" + uid + "\" alt=\"" + w + "x" + h
				+ "\" /><br/>\n";
	}

	private static Timestamp generateCreatedTimestamp() {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.HOUR_OF_DAY, -r.nextInt(24));
		cl.add(Calendar.MINUTE, -r.nextInt(60));
		cl.add(Calendar.DAY_OF_MONTH, -r.nextInt(30) - accountCount);
		cl.add(Calendar.MONTH, -r.nextInt(12));
		cl.add(Calendar.YEAR, -r.nextInt(1));
		return new Timestamp(cl.getTimeInMillis());
	}

	private static Timestamp randomizeTimeForCreated(Timestamp timestamp) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(timestamp.getTime());
		cl.add(Calendar.HOUR_OF_DAY, -r.nextInt(24));
		cl.add(Calendar.MINUTE, -r.nextInt(60));
		return new Timestamp(cl.getTimeInMillis());
	}

	private static String generateArticleDesc(String title) {
		int repeat = r.nextInt(5) + 1;
		StringBuilder desc = new StringBuilder("<p>");
		for (int i = 0; i < repeat; i++) {
			desc.append(title).append(" ");
		}
		if (desc.length() >= 255 - 5) {
			desc.delete(255 - 5, desc.length());
		}
		return desc.toString().trim() + "</p>";
	}

	private static void appendParagraph(StringBuilder content, List<String> sentences) {
		int repeat = r.nextInt(9) + 1;
		for (int i = 0; i < repeat; i++) {
			Collections.shuffle(sentences);
			content.append("<p>");
			List<String> paragraph = sentences.subList(0, r.nextInt(sentences.size()));
			for (String sentence : paragraph) {
				content.append(sentence).append(" ");
			}
			if (!paragraph.isEmpty()) {
				content.deleteCharAt(content.length() - 1);
			}
			content.append("</p>\n");
		}
	}

	private static String generateArticleContent(String title, String desc, String theme) throws IOException {
		boolean withImg = r.nextBoolean();
		int imgCount = 0;
		if (withImg) {
			imgCount = r.nextInt(4) + 1;
		}
		int otherSentences = r.nextInt(14) + 6;
		List<String> sentences = new ArrayList<>();
		sentences.add(title);
		while (sentences.size() != otherSentences + 1) {
			String sentence = SENTENCES.get(r.nextInt(SENTENCES.size()));
			if (!sentences.contains(sentence)) {
				sentences.add(sentence);
			}
		}
		StringBuilder content = new StringBuilder(desc + "\n");
		if (withImg) {
			for (int j = 0; j < imgCount; j++) {
				appendParagraph(content, sentences);
				content.append(generateContentImageHtml(theme));
				appendParagraph(content, sentences);
			}
		} else {
			appendParagraph(content, sentences);
		}
		return content.toString();
	}

	private static List<ArticleItem> generateArticles(Connection c, List<CategoryItem> categories)
			throws SQLException, IOException {
		List<ArticleItem> articles = new ArrayList<>();
		try (PreparedStatement ps = c.prepareStatement("insert into article values (?,?,?,?,?,?,?,?,?,?)")) {
			while (!categories.isEmpty()) {
				CategoryItem item = categories.get(r.nextInt(categories.size()));
				String title = SENTENCES.get(r.nextInt(SENTENCES.size()));
				if (title.length() > 80) {
					title = title.substring(0, 80);
				}
				String theme = IMG_THEME[r.nextInt(IMG_THEME.length)];
				String desc = generateArticleDesc(title);
				String content = generateArticleContent(title, desc, theme);
				int comments = r.nextInt(accountCount);
				Timestamp created = generateCreatedTimestamp();

				ps.setLong(1, idArticle);
				ps.setString(2, title);
				ps.setString(3, createArticleUrl(title));
				ps.setString(4, generateArticleMainImageLink(theme));
				ps.setString(5, desc);
				ps.setString(6, content);
				ps.setInt(7, item.id);
				ps.setTimestamp(8, created);
				ps.setLong(9, r.nextInt(10000));
				ps.setInt(10, comments);
				ps.addBatch();
				articles.add(new ArticleItem(idArticle, comments, created));
				idArticle++;
				item.articles--;
				if (item.articles == 0) {
					categories.remove(item);
				}
				if (idArticle % 20 == 0) {
					System.out
							.println("Generated " + (idArticle - START_ARTICLE_ID) + " articles from " + articleCount);
					ps.executeBatch();
					c.commit();
				}
			}
			ps.executeBatch();
			c.commit();
		}
		System.out.println("Articles created: " + articles.size());
		return articles;
	}

	private static void generateAccounts(Connection c) throws SQLException, IOException {
		List<String> names = new ArrayList<String>(Arrays.asList(NAMES));
		try (PreparedStatement ps = c.prepareStatement("insert into account values (nextval('account_seq'),?,?,?,?)")) {
			for (int i = 0; i < accountCount; i++) {
				String name = names.remove(r.nextInt(names.size()));
				ps.setString(1, name.toLowerCase() + "@myblog.test");
				ps.setString(2, name);
				ps.setString(3, generateAccountAvatar());
				ps.setTimestamp(4, generateCreatedTimestamp());
				ps.addBatch();
			}
			ps.executeBatch();
			c.commit();
			System.out.println("Accounts created: " + accountCount);
		}
	}

	private static String generateCommentContent() {
		int sencencesCount = r.nextInt(6) + 1;
		List<String> sentences = new ArrayList<>(SENTENCES);
		Collections.shuffle(sentences);
		StringBuilder content = new StringBuilder(sentences.remove(0));
		for (int i = 0; i < sencencesCount - 1; i++) {
			content.append(" ").append(sentences.remove(0));
		}
		return content.toString();
	}

	private static void generateComments(Connection c, List<ArticleItem> articles) throws SQLException {
		int count = 0;
		try (PreparedStatement ps = c.prepareStatement("insert into comment values (nextval('comment_seq'),?,?,?,?)")) {
			for (ArticleItem item : articles) {
				if (item.comments > 0) {
					List<Integer> idAccounts = new ArrayList<>();
					for (int i = 0; i < accountCount; i++) {
						idAccounts.add(i + 1);
					}
					Collections.shuffle(idAccounts);

					Timestamp startTime = item.created;
					Timestamp endTime = new Timestamp(System.currentTimeMillis());
					int intervalBetweenCommentsInDays = (int) (((endTime.getTime() - startTime.getTime())
							/ item.comments) / MILISECONDS_IN_DAY);

					for (int i = 0; i < item.comments; i++) {
						ps.setLong(2, item.id);
						Integer idAccount = idAccounts.remove(r.nextInt(idAccounts.size()));
						ps.setLong(1, idAccount.longValue());
						ps.setString(3, generateCommentContent());
						Timestamp created = new Timestamp(
								startTime.getTime() + r.nextInt(intervalBetweenCommentsInDays) * MILISECONDS_IN_DAY);
						ps.setTimestamp(4, randomizeTimeForCreated(created));
						startTime = new Timestamp(
								startTime.getTime() + intervalBetweenCommentsInDays * MILISECONDS_IN_DAY);
						ps.addBatch();
						count++;
					}
					ps.executeBatch();
					c.commit();
				}
			}
			System.out.println("Comments created: " + count);
		}
	}

	private static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] dir = file.listFiles();
				if (dir != null) {
					for (File f : dir) {
						deleteFile(f);
					}
				}
				file.delete();
			}
		}
	}

	private static void deleteMediaDir() {
		deleteFile(new File(DEST_MEDIA));
	}

	private static void downloadImageFromInternet(String url, String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try (InputStream in = new URL(url).openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
	}

	public static void main(String[] args) {
		init();
		deleteMediaDir();
		try (Connection c = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
			c.setAutoCommit(false);
			clearDb(c);
			List<CategoryItem> categories = generateCategories(c);
			accountCount = (articleCount * 1) / 5;
			System.out.println("TODO: articleCount=" + articleCount + ", accountCount=" + accountCount);
			generateAccounts(c);
			List<ArticleItem> articles = generateArticles(c, categories);
			generateComments(c, articles);
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getNextException() != null) {
				e.getNextException().printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class CategoryItem {
		private final int id;
		private int articles;

		private CategoryItem(int id, int articles) {
			super();
			this.id = id;
			this.articles = articles;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CategoryItem other = (CategoryItem) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}

	private static class ArticleItem {
		private final int id;
		private final int comments;
		private final Timestamp created;

		private ArticleItem(int id, int comments, Timestamp created) {
			super();
			this.id = id;
			this.comments = comments;
			this.created = created;
		}
	}
}