package main;

import javax.swing.JFrame;

import com.jogamp.opengl.GLEventListener;

public abstract class HelloOpenGL2 extends JFrame implements GLEventListener{
	public static void main(String[] args) {
		//HelloOpenGL2 hogl = new HelloOpenGL2();
	}
	private static final long serialVersionUID = 1L;// What is this???

	public HelloOpenGL2() {
		super("Minimal OpenGL");
		this.setName("Maximal OpenGL");
				
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.getGraphics().drawOval(100, 100, 50, 70);
		
		//final GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		
		//GLProfile.get(GLProfile.GL2);
		//GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		//GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		//GLCanvas glcanvas = new GLCanvas(glcapabilities);
		//this.getContentPane().add(glcanvas);
		//glcanvas.requestFocusInWindow();
		
		System.out.println("This still gets printed for some reason -- multithreading?");
	}
	/**
	@Override
	public void display(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
		// call your code here
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {}

	@Override
	public void init(GLAutoDrawable drawable) {}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	*/
}
