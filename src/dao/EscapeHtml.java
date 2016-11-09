package dao;

public class EscapeHtml {

	public static String escape(String s) {
		StringBuilder out = new StringBuilder(Math.max(1, s.length()));
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '<' || c == '>' || c == '&' || c == '/' || c == '\'') {
				out.append("&#");
				out.append((int) c);
				out.append(';');
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}

}
