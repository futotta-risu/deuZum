package deustoZumServer.Visual.Style.Layout;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * A flow layout arranges components in a directional flow, much
 * like lines of text in a paragraph. The flow direction is
 * determined by the container's <code>componentOrientation</code>
 * property and may be one of two values:
 * <ul>
 * <li><code>ComponentOrientation.TOP_TO_BOTTOM</code>
 * <li><code>ComponentOrientation.BOTTOM_TO_TOP</code>
 * </ul>
 * Flow layouts are typically used
 * to arrange buttons in a panel. It arranges buttons
 * horizontally until no more buttons fit on the same line.
 * The line alignment is determined by the <code>align</code>
 * property. The possible values are:
 * <ul>
 * <li>{@link #TOP TOP}
 * <li>{@link #BOTTOM BOTTOM}
 * <li>{@link #CENTER CENTER}
 * <li>{@link #LEADING LEADING}
 * <li>{@link #TRAILING TRAILING}
 * </ul>
 * <p>
 */
public class VerticalFlowLayout implements LayoutManager, java.io.Serializable{
    /**
     * This value indicates that each row of components
     * should be left-justified.
     */
    public static final int TOP     = 0;

    /**
     * This value indicates that each row of components
     * should be centered.
     */
    public static final int CENTER    = 1;

    /**
     * This value indicates that each row of components
     * should be right-justified.
     */
    public static final int BOTTOM     = 2;

    /**
     * <code>align</code> is the property that determines
     * how each column distributes empty space.
     * It can be one of the following three values:
     * <ul>
     * <code>TOP</code>
     * <code>BOTTOM</code>
     * <code>CENTER</code>
     * </ul>
     *
     * @see #getAlignment
     * @see #setAlignment
     */
    int align;     // This is the one we actually use

    /**
     * The flow layout manager allows a seperation of
     * components with gaps.  The horizontal gap will
     * specify the space between components and between
     * the components and the borders of the
     * <code>Container</code>.
     *
     * @see #getHgap()
     * @see #setHgap(int)
     */
    int hgap;

    /**
     * The flow layout manager allows a seperation of
     * components with gaps.  The vertical gap will
     * specify the space between rows and between the
     * the rows and the borders of the <code>Container</code>.
     *
     * @see #getHgap()
     * @see #setHgap(int)
     */
    int vgap;

    boolean fill = false;
    
    
    /**
     * 
     * Constructs a new <code>VerticalFlowLayout</code> with a centered alignment and a
     * default 5-unit horizontal and vertical gap.
     */
    
    
    
    public VerticalFlowLayout()
    {
        this(CENTER, 5, 5);
    }

    /**
     * Constructs a new <code>VerticalFlowLayout</code> with the specified
     * alignment and a default 5-unit horizontal and vertical gap.
     * The value of the alignment argument must be one of
     * <code>VerticalFlowLayout.TOP</code>, <code>VerticalFlowLayout.BOTTOM</code>,
     * or <code>VerticalFlowLayout.CENTER</code>
     * @param align the alignment value
     */
    public VerticalFlowLayout(int align)
    {
        this(align, 5, 5);
    }

    /**
     * Creates a new flow layout manager with the indicated alignment
     * and the indicated horizontal and vertical gaps.
     * <p>
     * The value of the alignment argument must be one of
     * <code>VerticalFlowLayout.TOP</code>, <code>VerticalFlowLayout.BOTTOM</code>,
     * or <code>VerticalFlowLayout.CENTER</code>.
     * @param     align   the alignment value
     * @param     hgap  the horizontal gap between components
     *                   and between the components and the
     *                   borders of the <code>Container</code>
     * @param     vgap  the vertical gap between components
     *                   and between the components and the
     *                   borders of the <code>Container</code>
     */
    public VerticalFlowLayout(int align, boolean fill) {
    	this.fill = fill;
    	this.hgap=0;
    	this.vgap=0;
    	setAlignment(align);
    }
    
    public VerticalFlowLayout(int align, int hgap, int vgap)
    {
        this.hgap = hgap;
        this.vgap = vgap;
        setAlignment(align);
    }

    /**
     * Gets the alignment for this layout.
     * Possible values are <code>VerticalFlowLayout.TOP</code>,
     * <code>VerticalFlowLayout.BOTTOM</code> or <code>VerticalFlowLayout.CENTER</code>,
     * @return   the alignment value for this layout
     * @see     java.awt.VerticalFlowLayout#setAlignment
     * @since     JDK1.1
     */
    public int getAlignment()
    {
        return align;
    }

    /**
     * Sets the alignment for this layout. Possible values are
     * <ul>
     * <li><code>VerticalFlowLayout.TOP</code>
     * <li><code>VerticalFlowLayout.BOTTOM</code>
     * <li><code>VerticalFlowLayout.CENTER</code>
     * </ul>
     * @param     align one of the alignment values shown above
     * @see     #getAlignment()
     * @since     JDK1.1
     */
    public void setAlignment(int align)
    {
        this.align = align;
    }

    /**
     * Gets the horizontal gap between components
     * and between the components and the borders
     * of the <code>Container</code>
     *
     * @return   the horizontal gap between components
     *           and between the components and the borders
     *           of the <code>Container</code>
     * @see     java.awt.VerticalFlowLayout#setHgap
     * @since     JDK1.1
     */
    public int getHgap() {
        return hgap;
    }

    /**
     * Sets the horizontal gap between components and
     * between the components and the borders of the
     * <code>Container</code>.
     *
     * @param hgap the horizontal gap between components
     *           and between the components and the borders
     *           of the <code>Container</code>
     * @see     java.awt.VerticalFlowLayout#getHgap
     * @since     JDK1.1
     */
    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    /**
     * Gets the vertical gap between components and
     * between the components and the borders of the
     * <code>Container</code>.
     *
     * @return   the vertical gap between components
     *           and between the components and the borders
     *           of the <code>Container</code>
     * @see     java.awt.VerticalFlowLayout#setVgap
     * @since     JDK1.1
     */
    public int getVgap() {
        return vgap;
    }

    /**
     * Sets the vertical gap between components and between
     * the components and the borders of the <code>Container</code>.
     *
     * @param vgap the vertical gap between components
     *           and between the components and the borders
     *           of the <code>Container</code>
     * @see     java.awt.VerticalFlowLayout#getVgap
     */
    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

    /**
     * Adds the specified component to the layout.
     * Not used by this class.
     * @param name the name of the component
     * @param comp the component to be added
     */
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * Removes the specified component from the layout.
     * Not used by this class.
     * @param comp the component to remove
     * @see    java.awt.Container#removeAll
     */
    public void removeLayoutComponent(Component comp) {
    }

    /**
     * Returns the preferred dimensions for this layout given the
     * <i>visible</i> components in the specified target container.
     *
     * @param target the container that needs to be laid out
     * @return  the preferred dimensions to lay out the
     *          subcomponents of the specified container
     * @see Container
     * @see #minimumLayoutSize
     * @see    java.awt.Container#getPreferredSize
     */
    public Dimension preferredLayoutSize(Container target){
	    synchronized (target.getTreeLock()){
	        Dimension dim = new Dimension(0, 0);
	        int nmembers = target.getComponentCount();
	        boolean firstVisibleComponent = true;
	        Insets insets = target.getInsets();
	        for (int i = 0 ; i < nmembers ; i++){
	            Component m = target.getComponent(i);
	
	            if (m.isVisible()){
	                Dimension d = m.getPreferredSize();
	                dim.width = Math.max(dim.width-(insets.left + insets.right + hgap*2), d.width);
	                
	                
	                if (firstVisibleComponent)
	                    firstVisibleComponent = false;
	                else
	                	dim.height += vgap;
	
	                dim.height += d.height;
	            }
	        }
	
	        
	        if(this.fill) {
	        	dim.width += insets.left + insets.right + hgap*2;
	            
	        }
	        dim.height += insets.top + insets.bottom + vgap*2;
	        return dim;
	    }
    }

    /**
     * Returns the minimum dimensions needed to layout the <i>visible</i>
     * components contained in the specified target container.
     * @param target the container that needs to be laid out
     * @return  the minimum dimensions to lay out the
     *          subcomponents of the specified container
     * @see #preferredLayoutSize
     * @see    java.awt.Container
     * @see    java.awt.Container#doLayout
     */
    public Dimension minimumLayoutSize(Container target){
    synchronized (target.getTreeLock())
    {
        Dimension dim = new Dimension(0, 0);
        int nmembers = target.getComponentCount();
        boolean firstVisibleComponent = true;

        for (int i = 0 ; i < nmembers ; i++)
        {
            Component m = target.getComponent(i);
            if (m.isVisible())
            {
                Dimension d = m.getMinimumSize();
                dim.width = Math.max(dim.width, d.width);

                if (firstVisibleComponent)
                {
                    firstVisibleComponent = false;
                }
                else
                {
                    dim.height += vgap;
                }

                dim.height += d.height;
            }
        }


        Insets insets = target.getInsets();
        dim.width += insets.left + insets.right + hgap*2;
        dim.height += insets.top + insets.bottom + vgap*2;
        return dim;
    }
    }

    /**
     * Lays out the container. This method lets each
     * <i>visible</i> component take
     * its preferred size by reshaping the components in the
     * target container in order to satisfy the alignment of
     * this <code>VerticalFlowLayout</code> object.
     *
     * @param target the specified component being laid out
     * @see Container
     * @see    java.awt.Container#doLayout
     */
    public void layoutContainer(Container target){
    synchronized (target.getTreeLock()){
        Insets insets = target.getInsets();
        Dimension d = target.getSize();
        //int maxHeight = target.getSize().height - (insets.top + insets.bottom + vgap*2);
        int maxHeight = 20000;
        int nmembers = target.getComponentCount();
        int x = 0;
        int y = 0;
        int columnWidth = d.width;
        int start = 0;

        boolean ttb = target.getComponentOrientation().isLeftToRight();
        
        for (int i = 0 ; i < nmembers ; i++){
            Component m = target.getComponent(i);

            if (m.isVisible()){
                Dimension d1 = m.getPreferredSize();
                
                
                if(!this.fill)
                	m.setSize(d.width-2*hgap-insets.left-insets.right, d1.height);
                else
                	m.setSize(d.width, d1.height);
                
                if ((y == 0) || ((y + d1.height) <= maxHeight)){
                    if (y > 0 && !this.fill)
                    	y += vgap;
                    

                    y += d1.height;
                    
                }
                else{
                    moveComponents(target, x, insets.top + vgap, columnWidth, maxHeight - y, start, i, ttb);
                    y = d1.height;
                    x += hgap + columnWidth;
                    columnWidth = d.width;
                    start = i;
                }
            }
        }

        moveComponents(target, x, insets.top + vgap, columnWidth, maxHeight - y, start, nmembers, ttb);
    }
    }

    /**
     * Centers the elements in the specified row, if there is any slack.
     * @param target the component which needs to be moved
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width dimensions
     * @param height the height dimensions
     * @param columnStart the beginning of the column
     * @param columnEnd the the ending of the column
     */
    private void moveComponents(
        Container target, int x, int y, int width, int height, int columnStart, int columnEnd, boolean ttb)
    {
        switch (align)
        {
            case TOP:
                y += ttb ? 0 : height;
                break;
            case CENTER:
                y += height / 2;
                break;
            case BOTTOM:
                y += ttb ? height : 0;
                break;
        }

        for (int i = columnStart ; i < columnEnd ; i++)
        {
            Component m = target.getComponent(i);

            if (m.isVisible()){
                int cx = x;
                if(!this.fill)
                	cx = x + (width - m.getSize().width) / 2;
                
                if (ttb)
                	m.setLocation(cx, y);
                
                else
                	m.setLocation(cx, target.getSize().height - y - m.getSize().height);
                

                y += m.getSize().height + vgap;
            }
        }
    }

    /**
     * Returns a string representation of this <code>VerticalFlowLayout</code>
     * object and its values.
     * @return   a string representation of this layout
     */
    public String toString()
    {
        String str = "";

        switch (align)
        {
            case TOP:    str = ",align=top"; break;
            case CENTER: str = ",align=center"; break;
            case BOTTOM: str = ",align=bottom"; break;
        }

        return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + str + "]";
    }

	


    


}
