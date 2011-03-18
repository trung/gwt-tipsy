package org.mdkt.common.client.utils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Tooltips, notification ... <br/>
 * 
 * .cw-Tipsy <br/>
 * .cw-Tipsy-inner <br/>
 * .cw-Tipsy-north <br/>
 * .cw-Tipsy-south<br/>
 * .cw-Tipsy-east<br/>
 * .cw-Tipsy-west<br/>
 * .cw-Tipsy-west .cw-Tipsy-inner<br/>
 * 
 * @author trung
 *
 */
public class Tipsy extends Composite {
    public static final String STYLE = "cw-Tipsy";
    public static final String NORTH = "north";
    public static final String SOUTH = "south";
    public static final String WEST = "west";
    public static final String EAST = "east";
    public static final String STYLE_INNER = STYLE + "-inner";

    private SimplePanel panel = new SimplePanel();
    private Label innerLabel = new Label();

    public Tipsy() {
        initWidget(panel);
        panel.setStylePrimaryName(STYLE);        
        panel.add(innerLabel);
        innerLabel.setStylePrimaryName(STYLE_INNER);
//        RootPanel.get().add(this);
//        this.setVisible(false);
    }
   
    /**
     * Show above the provided widget
     * @param widget
     */
    public void showNorthOf(Widget widget, String text) {
    	init(text);
    	clearStyleDependentName();
    	addStyleDependentName(SOUTH);
    	show();
    	int top = widget.getAbsoluteTop() - this.getOffsetHeight();
    	int left = widget.getAbsoluteLeft() - ((this.getOffsetWidth() - widget.getOffsetWidth()) / 2);
    	moveTo(top, left);
    }

    /**
     * Show on the right of the provided widget
     * @param widget
     */
    public void showEastOf(Widget widget, String text) {
    	init(text);
    	clearStyleDependentName();
    	addStyleDependentName(WEST);
    	show();
    	if (this.getOffsetWidth() + widget.getOffsetWidth() + widget.getAbsoluteLeft() > Window.getClientWidth()) {
    		showNorthOf(widget, text);
    		return;
    	}
    	int top = widget.getAbsoluteTop() - ((this.getOffsetHeight() - widget.getOffsetHeight())/2);
    	int left = widget.getAbsoluteLeft() + widget.getOffsetWidth() +  1;
    	moveTo(top, left);
    }

    /**
     * Show bellow the provided widget
     * @param widget
     */
    public void showSouthOf(Widget widget, String text) {
    	init(text);
    	clearStyleDependentName();
    	addStyleDependentName(NORTH);
    	show();
    	int top = widget.getAbsoluteTop() + widget.getOffsetHeight();
    	int left = widget.getAbsoluteLeft() - ((this.getOffsetWidth() - widget.getOffsetWidth()) / 2);
    	moveTo(top, left);
    }

    /**
     * Show on the left of the provided widget
     * @param widget
     */
    public void showWestOf(Widget widget, String text) {
    	init(text);
    	clearStyleDependentName();
    	addStyleDependentName(EAST);
    	show();
    	int top = widget.getAbsoluteTop() - ((this.getOffsetHeight() - widget.getOffsetHeight())/2);
    	int left = widget.getAbsoluteLeft() - 1;
    	moveTo(top, left);
    }

    private void clearStyleDependentName() {
		removeStyleDependentName(NORTH);
		removeStyleDependentName(SOUTH);
		removeStyleDependentName(WEST);
		removeStyleDependentName(EAST);
	}

	private void init(String text) {
		innerLabel.setText(text);
//		this.setVisible(true);
	}

	private void show() {
        Element elem = panel.getElement();
//        DOM.setStyleAttribute(elem, "left", String.valueOf(left) + "px");
//        DOM.setStyleAttribute(elem, "top", String.valueOf(top) + "px");
        DOM.setStyleAttribute(elem, "position", "absolute");
        RootPanel.get().add(this);
    }
	
	private void moveTo(int top, int left) {
		Element elem = panel.getElement();
      DOM.setStyleAttribute(elem, "left", String.valueOf(left) + "px");
      DOM.setStyleAttribute(elem, "top", String.valueOf(top) + "px");
	}

    public void hide() {
        RootPanel.get().remove(this);
    }
}

