package application;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;

import javax.swing.JLabel;

import javafx.application.Application;
import javafx.beans.value.ChangeListener; 
import javafx.beans.value.ObservableValue; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;

public class CS255 extends Application {
	short cthead[][][];
	short min, max;
    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
		stage.setTitle("CThead Viewer");
		ReadData();
		int width = 256;
        int height = 256;
		WritableImage medical_image1 = new WritableImage(width, height);
		WritableImage medical_image2 = new WritableImage(width, height);
		WritableImage medical_image3 = new WritableImage(width, height);
		WritableImage TopResize_image = new WritableImage(width, height);
		WritableImage FrontResize_image = new WritableImage(width, height);
		WritableImage SideResize_image = new WritableImage(width, height);
		WritableImage TopHistogram_image = new WritableImage(width, height);
		WritableImage FrontHistogram_image = new WritableImage(width, height);
		WritableImage SideHistogram_image = new WritableImage(width, height);
		WritableImage TopResize_Bilinear_Image = new WritableImage(width, height);
		WritableImage FrontResize_Bilinear_Image = new WritableImage(width, height);
		WritableImage SideResize_Bilinear_Image = new WritableImage(width, height);
		ImageView imageView = new ImageView(medical_image1);
		ImageView imageView2 = new ImageView(medical_image2);
		ImageView imageView3 = new ImageView(medical_image3);
		ImageView TopResizeView = new ImageView(TopResize_image);		
		ImageView FrontResizeView = new ImageView(FrontResize_image);
		ImageView SideResizeView = new ImageView(SideResize_image);
		ImageView TopHistogramView = new ImageView(TopHistogram_image);
		ImageView FrontHistogramView = new ImageView(FrontHistogram_image);
		ImageView SideHistogramView = new ImageView(SideHistogram_image);
		ImageView TopResize_Bilinear_view = new ImageView(TopResize_Bilinear_Image);
		ImageView FrontResizeBilinear_view = new ImageView(FrontResize_Bilinear_Image);
		ImageView SideResizeBilinear_view = new ImageView(SideResize_Bilinear_Image);
		
		
		Button mip_button1 = new Button("Mip_Top"); 
		Button mip_button2 = new Button("Mip_Front");
		Button mip_button3 = new Button("Mip_Side");
		Button mip_button4 = new Button("TopResize");
		Button mip_button5 = new Button("FrontResize");
		Button mip_button6 = new Button("SideResize");
		Button mip_button7 = new Button("Topinterpolation");
		Slider TopSlider = new Slider(0, 112, 0);
		Slider FrontSlider = new Slider(0, 255, 0);
		Slider SideSlider = new Slider(0, 255,0);
		Slider TopResizeSlider = new Slider (1,2,1);
		Slider FrontResizeSlider = new Slider (1,2,1);
		Slider SideResizeSlider = new Slider (1,2,1);
		Slider TopHistogramSlider = new Slider(0, 112, 0);
		Slider FrontHistogramSlider = new Slider(0, 255, 0);
		Slider SideHistogramSlider = new Slider(0, 255, 0);
		Slider TopBilinearSlider = new Slider(1,2,1);
		Slider FrontBilinearSlider = new Slider(1,2,1);
		Slider SideBilinearSlider = new Slider(1,2,1);
		
	
		mip_button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
			   mip_Top(medical_image1);
            }
        });
		
		mip_button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				mip_front(medical_image2);
            }
        });
		
		mip_button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				mip_side(medical_image3);
            }
        });
		mip_button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			     mip_Top(TopResize_image);
			}
		});
		mip_button5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			    mip_front(FrontResize_image);
			}
		});
		mip_button6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			     mip_side(SideResize_image);
			}
		});
		mip_button7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			     mip_Top(TopResize_Bilinear_Image);
			}
		});
		TopSlider.valueProperty().addListener( 
            new ChangeListener<Number>(){ 
				public void changed(ObservableValue <? extends Number >  
					observable, Number oldValue, Number newValue) 
            { 
			    topView(medical_image1, newValue.intValue());
                System.out.println(newValue.intValue());
            } 
        });
		
		FrontSlider.valueProperty().addListener( 
	            new ChangeListener<Number>() { 
					public void changed(ObservableValue <? extends Number >  
						observable, Number oldValue, Number newValue) 
	            { 
						frontView(medical_image2, newValue.intValue());
	                System.out.println(newValue.intValue());
	            } 
	        });
		
		SideSlider.valueProperty().addListener( 
	            new ChangeListener<Number>() { 
					public void changed(ObservableValue <? extends Number >  
						observable, Number oldValue, Number newValue) 
	            { 
						sideView(medical_image3, newValue.intValue());
	                System.out.println(newValue.intValue());
	            } 
	        });
		
		TopResizeSlider.valueProperty().addListener( 
	            new ChangeListener<Number>() { 
					public void changed(ObservableValue <? extends Number >  
						observable, Number oldValue, Number newValue) 
	            { 
						imageResize(TopResize_image, TopResizeView, newValue.doubleValue());
	                System.out.println(newValue.intValue());
	            } 
	        });
		
		FrontResizeSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue <? extends Number >
					observable, Number oldValue, Number newValue)
					{
						imageResize(FrontResize_image, FrontResizeView, newValue.doubleValue());
						System.out.println(newValue.intValue());
					}
				});
		SideResizeSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue <? extends Number >
					observable, Number oldValue, Number newValue)
					{
						imageResize(SideResize_image, SideResizeView, newValue.doubleValue());
						System.out.println(newValue.intValue());
					}
					
				});
		TopHistogramSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue <? extends Number >
					observable, Number oldValue, Number newValue)
					{
						histogram_equalizationTop(TopHistogram_image, newValue.intValue());
						System.out.println(newValue.intValue());
					}
					
				}
				);
		FrontHistogramSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue <? extends Number >
					observable, Number oldValue, Number newValue)
					{
						histogram_equalizationFront(FrontHistogram_image, newValue.intValue());
						System.out.println(newValue.intValue());
					}
					
				}
				);
		SideHistogramSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue <? extends Number >
					observable, Number oldValue, Number newValue)
					{
						histogram_equalizationSide(SideHistogram_image, newValue.intValue());
						System.out.println(newValue.intValue());
					}
				}
				);
		TopBilinearSlider.valueProperty().addListener( 
	            new ChangeListener<Number>() { 
					public void changed(ObservableValue <? extends Number >  
						observable, Number oldValue, Number newValue) 
	            { 
						System.out.println(newValue.floatValue());
					bilinearInterpolation(TopResize_Bilinear_Image,TopResize_Bilinear_view,  newValue.floatValue());
						//bilinearInterpolation(TopResize_Bilinear_Image,TopResize_Bilinear_view,  newValue.doubleValue());
	                //System.out.println(newValue.doubleValue());
	            } 
	        });
		GridPane gridPane = new GridPane();
		gridPane.addRow(0, mip_button1, mip_button2, mip_button3);
		gridPane.addRow(1, imageView, imageView2, imageView3);
		gridPane.addRow(2, TopSlider, FrontSlider, SideSlider);
		gridPane.addRow(3, mip_button4, mip_button5, mip_button6, mip_button7);
		gridPane.addRow(4, TopResizeView, FrontResizeView, SideResizeView, TopResize_Bilinear_view);
		gridPane.addRow(5, TopResizeSlider, FrontResizeSlider, SideResizeSlider, TopBilinearSlider);
		gridPane.addRow(6,  new Label("TopHistogram"), new Label("FrontHistogram"), new Label("SideHistogram"));
		gridPane.addRow(7, TopHistogramView, FrontHistogramView, SideHistogramView );
		gridPane.addRow(8, TopHistogramSlider, FrontHistogramSlider, SideHistogramSlider);
	
        
        Scene scene = new Scene(gridPane, 1200, 940);
        stage.setScene(scene);
        stage.show();
    }
	
	public void ReadData() throws IOException {
		File file = new File("src/application/CThead.raw");
		
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		
		int i, j, k; //loop through the 3D data set
		
		min=Short.MAX_VALUE; max=Short.MIN_VALUE; //set to extreme values
		short read; //value read in
		int b1, b2; //data is wrong Endian (check wikipedia) for Java so we need to swap the bytes around
		
		cthead = new short[113][256][256]; //allocate the memory - note this is fixed for this data set
		//loop through the data reading it in
		for (k=0; k<113; k++) {
			for (j=0; j<256; j++) {
				for (i=0; i<256; i++) {
					//because the Endianess is wrong, it needs to be read byte at a time and swapped
					b1=((int)in.readByte()) & 0xff; //the 0xff is because Java does not have unsigned types
					b2=((int)in.readByte()) & 0xff; //the 0xff is because Java does not have unsigned types
					read=(short)((b2<<8) | b1); //and swizzle the bytes around
					if (read<min) min=read; //update the minimum
					if (read>max) max=read; //update the maximum
					cthead[k][j][i]=read; //put the short into memory (in C++ you can replace all this code with one fread)
				}
			}
		}
		System.out.println(min+" "+max); //diagnostic - for CThead this should be -1117, 2248
		//(i.e. there are 3366 levels of grey (we are trying to display on 256 levels of grey)
		//therefore histogram equalization would be a good thing
	}
	
	
    public void topView(WritableImage image, int z) {
            int  i, j, c;
            PixelWriter image_writer = image.getPixelWriter();
			float col;
			short datum;
            for (j=0; j<cthead[0].length; j++) {
                    for (i=0; i<cthead[0][0].length; i++) {
							datum=cthead[z][j][i]; 
							short maximum = -32758;
							if(datum > maximum) {
								maximum = datum;
							}
							col=(((float)maximum-(float)min)/((float)(max-min)));
                            for (c=0; c<3; c++) {
								image_writer.setColor(i, j, Color.color(col,col,col, 1.0));
                            } 
                    } 
            } 
    }
    
    public void frontView(WritableImage image, int y) {
        int  i, c, f;
        PixelWriter image_writer = image.getPixelWriter();
		float col;
		short datum;
        for (f=0; f<cthead[0].length; f++) {
                for (i=0; i<cthead[0][0].length; i++) {
                	float l = f;
                	l = l/256 * 113;
                	int k = (int)l;
                	short maximum = -32758;
						datum=cthead[k][y][i]; 
						if(datum > maximum) {
							maximum = datum;
						}
						col=(((float)maximum-(float)min)/((float)(max-min)));
                        for (c=0; c<3; c++) {
							image_writer.setColor(i, f, Color.color(col,col,col, 1.0));
                        } 
                } 
        } 
}
    public void sideView(WritableImage image, int x) {
        int  j, c, f;
        PixelWriter image_writer = image.getPixelWriter();
		float col;
		short datum;
        
        for (f=0; f<cthead[0].length; f++) {
                for (j=0; j<cthead[0][0].length; j++) {
                	
                	float n = f;
                	n = n/256 * 113;
                	int k = (int)n;
                	short maximum = -32758;	
						datum=cthead[k][j][x]; 
						if(datum > maximum) {
							maximum = datum;
						}
						col=(((float)maximum-(float)min)/((float)(max-min)));
                        for (c=0; c<3; c++) {
							image_writer.setColor(j, f, Color.color(col,col,col, 1.0));
                        } 
                        } 
        }
}
    public void imageResize(WritableImage image, ImageView resizeView, double newSize) {
        double width=  image.getWidth();
        double height=  image.getHeight();
    	double newW =  (width*newSize);
    	double newH =  (height*newSize);
    	int y,x;
		Color col;
	    Color[][] oldPixels = new Color[(int)width][(int)height];
	    WritableImage writable_TopResize = new WritableImage((int)newW,(int)newH);
	    PixelWriter image_writer = writable_TopResize.getPixelWriter();
	    for(y=0 ; y < cthead[0].length; y++) {
	    	for(x = 0; x < cthead[0][0].length; x++) {
	    		oldPixels[y][x] = image.getPixelReader().getColor(x,y);
	    	}
	    }
        for (y=0; y < newH-1; y++) {
                for (x = 0; x < newW-1; x++) {
                	double floatY = y * (height/newH);
                	double floatX = x * (width/newW);
                	int newY = (int) floatY;
                	int newX = (int) floatX;
             		col=oldPixels[newY][newX];
					image_writer.setColor(x, y, col);
                } 
        }
        resizeView.setImage(writable_TopResize);
        }
        
       
    public void mip_Top(WritableImage image) {
        int width=(int) image.getWidth(), height=(int) image.getHeight(), i, j, c, b;
        PixelWriter image_writer = image.getPixelWriter();
		float col;
		short datum;
        for (j=0; j<cthead[0].length; j++) {
                for (i=0; i<cthead[0][0].length; i++) {
                	
                	short maximum = -32758;
                	
                	for(b=0; b<113; b++) {
						datum=cthead[b][j][i];
						if (datum > maximum) {
							maximum = datum;
							
						}
                	}
						col=(((float)maximum-(float)min)/((float)(max-min)));
					
							for (c=0; c<3; c++) {
								image_writer.setColor(i, j, Color.color(col,col,col, 1.0));
	                        } 
                } 
        } 
}
    public void mip_front(WritableImage image) {
        int width=(int) image.getWidth(), height=(int) image.getHeight(), i, j, c, b,f;
        PixelWriter image_writer = image.getPixelWriter();
		float col;
		short datum;
        for (f=0; f<cthead[0].length; f++) {
                for (i=0; i<cthead[0][0].length; i++) {
                	float l = f;
                	l = l/256 * 113;
                	int k = (int)l;
                	short maximum = -32758;
                	for(b=0; b<255; b++) {
						datum=cthead[k][b][i];
						if (datum > maximum) {
							maximum = datum;
						}
                	}
						col=(((float)maximum-(float)min)/((float)(max-min)));
					
							for (c=0; c<3; c++) {
								image_writer.setColor(i, f, Color.color(col,col,col, 1.0));
	                        } 
                } 
        } 
}
    public void mip_side(WritableImage image) {
        int width=(int) image.getWidth(), height=(int) image.getHeight(), i, j, c, b,f;
        PixelWriter image_writer = image.getPixelWriter();
		float col;
		short datum;
        for (f=0; f<cthead[0].length; f++) {
                for (j=0; j<cthead[0][0].length; j++) {
                	float l = f;
                	l = l/256 * 113;
                	int k = (int)l;
                	short maximum = -32768;
                	for(b=0; b<255; b++) {
						datum=cthead[k][j][b];
						if (datum > maximum) {
							maximum = datum;
						}
                	}
                	
					        col=(((float)maximum-(float)min)/((float)(max-min)));
							for (c=0; c<3; c++) {
								image_writer.setColor(j, f, Color.color(col,col,col, 1.0));
	                    } 
            } 
     } 
}
    public void histogram_equalizationTop(WritableImage image, int k) {
    	PixelWriter image_writer = image.getPixelWriter();
    	int index = 0;
    	int histogram [] = new int [max - min + 1];
    	float col;
    	short datum;
    	System.out.println(max);
    	System.out.println(min);
    	
    	for(int d = 0; d < cthead.length; d++) {
    		for(int e = 0; e < cthead[d].length; e++) {
    			for(int g = 0; g < cthead[d][e].length; g++) {
    				index = cthead[d][e][g] - min;
    				histogram[index]++;
    			}
    		}
    	}
    	int t[] = new int[histogram.length];
    	t[0] = histogram[0];
    	float mapping[] = new float[histogram.length];
    	for(int x = 1; x < (max - min + 1); x++) {
    		t[x] = t[x-1] + histogram[x];
    		//mapping function
    		mapping[x] = (t[x]/(float)7405568);
    	}
    for(int j = 0; j < cthead[0].length ; j++) {
    	for (int i = 0; i < cthead[0][0].length ; i++) {
    		datum = cthead[k][j][i];
    		col = mapping[datum - min];
    		image_writer.setColor(i, j, Color.color(col,col,col, 1.0));
    	}
    }
    }
    public void histogram_equalizationFront(WritableImage image, int k) {
    	PixelWriter image_writer = image.getPixelWriter();
    	int index = 0;
    	int histogram [] = new int [max - min + 1];
    	float col;
    	short datum;
    	
    
    	for(int d = 0; d < cthead.length; d++) {
    		for(int e = 0; e < cthead[d].length; e++) {
    			for(int g = 0; g < cthead[d][e].length; g++) {
    				index = cthead[d][e][g] - min;
    				histogram[index]++;
    			}
    		}
    	}
    	
    	int t[] = new int[histogram.length];
    	t[0] = histogram[0];
    	float mapping[] = new float[histogram.length];
    	for(int x = 1; x < (max - min + 1); x++) {
    		t[x] = t[x-1] + histogram[x];
    		
    		mapping[x] = (t[x]/(float)7405568);
    	}
    for(int f = 0; f < cthead[0].length ; f++) {
    	for (int i = 0; i < cthead[0][0].length; i++) {
    		float l = f;
    		l = l/256 * 113;
    		int z = (int)l;
    		datum = cthead[z][k][i];
    		col = mapping[datum - min];
    		image_writer.setColor(i, f, Color.color(col,col,col, 1.0));
    	}
    }
    }
    public void histogram_equalizationSide(WritableImage image, int k) {
    	PixelWriter image_writer = image.getPixelWriter();
    	int index = 0;
    	int histogram [] = new int [max - min + 1];
    	float col;
    	short datum;
    	
    	for(int d = 0; d < cthead.length; d++) {
    		for(int e = 0; e < cthead[d].length; e++) {
    			for(int g = 0; g < cthead[d][e].length; g++) {
    				index = cthead[d][e][g] - min;
    				histogram[index]++;
    			}
    		}
    	}
    	
    	int t[] = new int[histogram.length];
    	t[0] = histogram[0];
    	float mapping[] = new float[histogram.length];
    	for(int x = 1; x < (max - min + 1); x++) {
    		t[x] = t[x-1] + histogram[x];
    		
    		mapping[x] = (t[x]/(float)7405568);
    	}
    for(int f = 0; f < cthead[0].length ; f++) {
    	for (int i = 0; i < cthead[0][0].length ; i++) {
    		float l = f;
    		l = l/256 * 113;
    		int z = (int)l;
    		datum = cthead[z][i][k];
    		col = mapping[datum - min];
    		image_writer.setColor(i, f, Color.color(col,col,col, 1.0));
    	}
    }
    }
    public void bilinearInterpolation(WritableImage image, ImageView imageView, float newSize) {
    	//System.out.println(newSize);
    	int height =  (int) image.getWidth();
    	int width =  (int) image.getHeight();
    	int newHeight =  (int) (height*newSize);
    	int newWidth =   (int) (width*newSize);
    	imageView.setFitHeight(newHeight);
    	imageView.setFitWidth(newWidth);
    	WritableImage BilinearImage = new WritableImage(newWidth,newHeight);
    	PixelWriter image_writer = BilinearImage.getPixelWriter();
    	for(int i = 0; i < newHeight; i++ ) {
    		for(int j=0; j < newWidth ; j++) {
    			double x = i*((double)width/(double)newWidth);
    			double y = j*((double)height/(double)newHeight);
    			//System.out.println(x);
    			//System.out.println(y);
    			double x1 = (Math.floor(x) + 1);
    			double x0 =  Math.floor(x);
    			double y1 = (Math.floor(y) + 1);
    			double y0 =  Math.floor(y);
    			if(x1 > 255 || y1 > 255) {
    				x1 = 255;
    				y1 = 255;
    			double v1 = image.getPixelReader().getColor((int)x0,(int)y0).getBlue();
    			double v2 = image.getPixelReader().getColor((int)x1,(int)y0).getBlue();
    			double v3 = image.getPixelReader().getColor((int)x0,(int)y1).getBlue();
    			double v4 = image.getPixelReader().getColor((int)x1,(int)y1).getBlue();
    			double v5 = v1 + (v2 - v1) * (x - x0)/(x1 - x0);
    			double v6 = v3 + (v4 - v3) * (x - x0)/(x1 - x0);
    			double col = v5 + (v6 - v5) * (y - y0)/(y1 - y0);
    			image_writer.setColor(i, j, Color.color(col,col,col, 1.0));
    			}
    			else {
    				double v1 = image.getPixelReader().getColor((int)x0,(int)y0).getBlue();
        			double v2 = image.getPixelReader().getColor((int)x1,(int)y0).getBlue();
        			double v3 = image.getPixelReader().getColor((int)x0,(int)y1).getBlue();
        			double v4 = image.getPixelReader().getColor((int)x1,(int)y1).getBlue();
        			double v5 = v1 + (v2 - v1) * (x - x0)/(x1 - x0);
        			double v6 = v3 + (v4 - v3) * (x - x0)/(x1 - x0);
        			double col = v5 + (v6 - v5) * (y - y0)/(y1 - y0);
        			image_writer.setColor(i, j, Color.color(col,col,col, 1.0));
    			}
    			}
    			}
    	imageView.setImage(BilinearImage);
    }
    /*public void bilinearInterpolation2(WritableImage image, ImageView imageView, double newSize) {
    	int w = (int)image.getWidth(), h = (int)image.getHeight(), i, j, c;
    	int xb = (int)(h * newSize);
    	int yb = (int)(w * newSize);
    	WritableImage BilinearImage = new WritableImage(xb,yb);
    	for( j = 0; j < yb; j++ ) {
    		for( i=0; i < xb ; i++) {
    			float y = j*(cthead[0][0].length/(float)yb);
    			float x = i * (cthead[0].length/(float)xb);
    			double x1 = Math.floor(x) + 1;
    			double x0 =  Math.floor(x);
    			double y1 = (Math.floor(y) + 1);
    			double y0 =  Math.floor(y);
    			if(x1> 255 || y1 > 255) {
    			x1 = 255;
    			y1 = 255;
    			double v1 = image.getPixelReader().getColor((int)x0,(int)y0).getBlue();
    			double v2 = image.getPixelReader().getColor((int)x1,(int)y0).getBlue();
    			double v3 = image.getPixelReader().getColor((int)x0,(int)y1).getBlue();
    			double v4 = image.getPixelReader().getColor((int)x1,(int)y1).getBlue();
    			double v5 = v1 + (v2 - v1) * ((x - x0)/(x1 - x0));
    			double v6 = v3 + (v4 - v3) * ((x - x0)/(x1 - x0));
    			double col = v5 + (v6 - v5) * ((y - y0)/(y1 - y0));
    			BilinearImage.getPixelWriter().setColor(i, j, Color.color(col, col, col, 1.0));
    			}
    			else {
    				double v1 = image.getPixelReader().getColor((int)x0,(int)y0).getBlue();
        			double v2 = image.getPixelReader().getColor((int)x1,(int)y0).getBlue();
        			double v3 = image.getPixelReader().getColor((int)x0,(int)y1).getBlue();
        			double v4 = image.getPixelReader().getColor((int)x1,(int)y1).getBlue();
        			double v5 = v1 + (v2 - v1) * ((x - x0)/(x1 - x0));
        			double v6 = v3 + (v4 - v3) * ((x - x0)/(x1 - x0));
        			double col = v5 + (v6 - v5) * ((y - y0)/(y1 - y0));
        			BilinearImage.getPixelWriter().setColor(i, j, Color.color(col, col, col, 1.0));
    			}
    			}
    	}
    	imageView.setImage(BilinearImage);
    }*/
        
    public static void main(String[] args) {
        launch();
    }
}
