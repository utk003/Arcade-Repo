import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class ShapeDisplay extends JComponent
{
	private JFrame frame;
	private Color color = Color.black;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public ShapeDisplay()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setPreferredSize(new Dimension(600, 600));
		frame.getContentPane().add(this);

		frame.pack();
		frame.setVisible(true);
	}

	public void setTitle(String title)
	{
		frame.setTitle(title);
	}

	public void setBackgroundColor(Color color)
	{
		this.color = color;
	}

	public void add(Shape shape)
	{
		shapes.add(shape);
		repaint();
	}

	public Iterator<Shape> shapes()
	{
		return shapes.iterator();
	}

	public void addKeyListener(KeyListener listener)
	{
		frame.addKeyListener(listener);
	}

	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		for (int i = 0; i < shapes.size(); i++)
		{
			Shape shape = shapes.get(i);
			g2.setColor(shape.getColor());

			double x = convertX(shape.getX());
			double y = convertY(shape.getY());
			double width = convertX(shape.getWidth());
			double height = convertY(shape.getHeight());

			if (shape.isRound())
				g2.fill(new Ellipse2D.Double(x, y, width, height));
			else
				g2.fill(new Rectangle2D.Double(x, y, width, height));
		}
	}

	private double convertX(double x)
	{
		return x / 100.0 * getWidth();
	}

	private double convertY(double y)
	{
		return y /100.0 * getHeight();
	}
}