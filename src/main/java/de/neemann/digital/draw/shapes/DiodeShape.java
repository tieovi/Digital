package de.neemann.digital.draw.shapes;

import de.neemann.digital.core.Observer;
import de.neemann.digital.core.element.ElementAttributes;
import de.neemann.digital.core.element.Keys;
import de.neemann.digital.core.element.PinDescriptions;
import de.neemann.digital.draw.elements.IOState;
import de.neemann.digital.draw.elements.Pin;
import de.neemann.digital.draw.elements.Pins;
import de.neemann.digital.draw.graphics.Graphic;
import de.neemann.digital.draw.graphics.Polygon;
import de.neemann.digital.draw.graphics.Style;
import de.neemann.digital.draw.graphics.Vector;

import static de.neemann.digital.draw.shapes.GenericShape.SIZE;
import static de.neemann.digital.draw.shapes.GenericShape.SIZE2;

/**
 * The diodes shape
 */
public class DiodeShape implements Shape {

    private final PinDescriptions outputs;
    private final boolean blown;

    /**
     * Creates a new instance
     *
     * @param attributes the attributes
     * @param inputs     the inputs
     * @param outputs    the outputs
     */
    public DiodeShape(ElementAttributes attributes, PinDescriptions inputs, PinDescriptions outputs) {
        this.outputs = outputs;
        blown = attributes.get(Keys.BLOWN);
    }

    @Override
    public Pins getPins() {
        return new Pins()
                .add(new Pin(new Vector(0, 0), outputs.get(0)))
                .add(new Pin(new Vector(0, -SIZE), outputs.get(1)));
    }

    @Override
    public InteractorInterface applyStateMonitor(IOState ioState, Observer guiObserver) {
        return null;
    }

    @Override
    public void drawTo(Graphic graphic, boolean highLight) {
        Style style = blown ? Style.DASH : Style.NORMAL;

        graphic.drawPolygon(
                new Polygon(true)
                        .add(-SIZE2, -SIZE + 1)
                        .add(SIZE2, -SIZE + 1)
                        .add(0, -1),
                style
        );
        graphic.drawLine(new Vector(-SIZE2, -1), new Vector(SIZE2, -1), style);
    }

}
