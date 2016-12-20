/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

/**
 *
 * @author amrsilva
 */
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroTexto extends DocumentFilter {
    int maxCharacters;
    boolean intOnly = false;
    public FiltroTexto(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    public FiltroTexto(int maxCharacters, boolean intOnly) {
        this.maxCharacters = maxCharacters;
        this.intOnly = intOnly;
    }
    
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
      AttributeSet attr) throws BadLocationException {
    /*System.out.println("insert string"+ string);
    System.out.println(offset);
    super.insertString(fb, offset, string, attr);*/
    //This rejects the entire insertion if it would make
    //the contents too long. Another option would be
    //to truncate the inserted string so the contents
    //would be exactly maxCharacters in length.
    if (intOnly)
        if ((fb.getDocument().getLength() + string.length()) <= maxCharacters && string.matches("^[1]?[0-9]{1,2}([.][0-9]{0,2})?$"))
            super.insertString(fb, offset, string, attr);
        else
            Toolkit.getDefaultToolkit().beep();
    else
        if ((fb.getDocument().getLength() + string.length()) <= maxCharacters )
            super.insertString(fb, offset, string, attr);
        else
            Toolkit.getDefaultToolkit().beep();
  }
    
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
        AttributeSet attrs) throws BadLocationException {
        if (text.equals("")){
            remove(fb, 0, fb.getDocument().getLength());
            return;
        }
        if (intOnly)
            if ((fb.getDocument().getLength() + text.length()) <= maxCharacters && text.matches("^[1]?[0-9]{1,2}([.][0-9]{0,2})?$") )
                super.insertString(fb, offset, text, attrs);
            else
                Toolkit.getDefaultToolkit().beep();
        else
            if ((fb.getDocument().getLength() + text.length()) <= maxCharacters )
                super.insertString(fb, offset, text, attrs);
            else
                Toolkit.getDefaultToolkit().beep();
    }
}