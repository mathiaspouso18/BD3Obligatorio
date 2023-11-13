package grafica.ventanas.UtilVentana;
import javax.swing.*;
import javax.swing.text.*;

public class CampoTextoNumerico extends JTextField {
	
	private static final int LARGO_MAXIMO = 8;

	public CampoTextoNumerico() {
		super();
		((PlainDocument) this.getDocument()).setDocumentFilter(new FiltroNumerico());
	}

	private class FiltroNumerico extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
			if (string == null) {
				return;
			}
			if (string.matches("\\d*")) { // Permite la inserción de texto solo si es numérico
				if ((fb.getDocument().getLength() + string.length()) <= LARGO_MAXIMO) { // Verifica la longitud máxima
					super.insertString(fb, offset, string, attr);
				}
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
			if (text.matches("\\d*")) {
				if ((fb.getDocument().getLength() - length + text.length()) <= LARGO_MAXIMO) { // Ajusta la longitud del reemplazo
					super.replace(fb, offset, length, text, attrs);
				}
			}
		}
	}
}
