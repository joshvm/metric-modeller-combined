package mohawk.co858.metricmodeller.ui.res;

import javafx.scene.image.Image;

public final class Res {

    public static final Image[] ICONS = {
            new Image(Res.class.getResourceAsStream("icon_64.png")),
            new Image(Res.class.getResourceAsStream("icon_48.png")),
            new Image(Res.class.getResourceAsStream("icon_32.png"))
    };

    private Res(){}
}
