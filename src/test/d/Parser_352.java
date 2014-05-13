package test.d;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

public class Parser_352 {

	public static void main(String[] args) throws Exception {
		Parser p = new Parser();
		p.setEncoding("UTF-8");
		p.setResource("352");
		
		NodeList list = p.extractAllNodesThatMatch(new CssSelectorNodeFilter("div[class='rzbcollect_list']"));
		
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			for (int j = 0; j < node.getChildren().size(); j++) {
				Node child = node.getChildren().elementAt(j);
				if (child instanceof Div) {
					for (int k = 0; k < child.getChildren().size(); k++) {
						Node c_node = child.getChildren().elementAt(k);
						if (c_node instanceof Span)
							System.out.println(((Span)c_node).getStringText());
						else if (c_node instanceof LinkTag)
							System.out.println(((LinkTag)c_node).getLink());
					}
				} else if (child instanceof TableTag) {
					TableTag table = (TableTag)child;
					System.out.println("发布时间\t" + table.getRow(0).getColumns()[3].getStringText().trim());
					System.out.println("项目管理方\t" + table.getRow(1).getColumns()[1].getStringText().split(" ")[0].trim());
					System.out.println("合作金额\t" + table.getRow(2).getColumns()[1].getStringText().trim());
					System.out.println("合作期限\t" + table.getRow(2).getColumns()[3].getStringText().trim());

					System.out.println("是否满额\t" + table.getRow(3).getColumns()[1].getStringText().trim());
					System.out.println("满额日期\t" + table.getRow(3).getColumns()[3].getStringText().trim());

					System.out.println("是否结算\t" + table.getRow(4).getColumns()[1].getStringText().trim());
					System.out.println("结算日期\t" + table.getRow(4).getColumns()[3].getStringText().trim());
				}
				
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    " + i + "      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			String title = node.getFirstChild().toHtml(); //.getFirstChild().getText();
//			Node link = node.getLastChild();//.getFirstChild();
//			
//			System.out.println(title);
//			System.out.println(link.getClass().getName());
//			node.
//			System.out.println(node.toHtml());
//			break;
		}
	}

}