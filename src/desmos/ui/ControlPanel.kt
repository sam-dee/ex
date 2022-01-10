package desmos.ui

import paints.CartesianPlane
import java.awt.Color
import javax.swing.*

class ControlPanel : JPanel() {
    val XMin: JLabel = JLabel("XMin:")
    val XMax: JLabel = JLabel("XMax:")
    val YMin: JLabel = JLabel("YMin:")
    val YMax: JLabel = JLabel("YMax:")
    val set1: JLabel = JLabel("Отображать точки")
    val set2: JLabel = JLabel("Отображать график функции")
    val set3: JLabel = JLabel("Отображать производную")
    val xMinM: SpinnerNumberModel = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
    val xMaxM: SpinnerNumberModel = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
    val yMinM: SpinnerNumberModel = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
    val yMaxM: SpinnerNumberModel = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
    val xMin: JSpinner = JSpinner(xMinM)
    val xMax: JSpinner = JSpinner(xMaxM)
    val yMin: JSpinner = JSpinner(yMinM)
    val yMax: JSpinner = JSpinner(yMaxM)
    val tf: JTextField = JTextField()
    val cb1: JCheckBox = JCheckBox()
    val cb2: JCheckBox = JCheckBox()
    val cb3: JCheckBox = JCheckBox()
    val cjp1: JPanel = JPanel()
    val cjp2: JPanel = JPanel()
    val cjp3: JPanel = JPanel()


    init {
    // region colors
        background = Color.WHITE

        cjp1.background = Color.RED
        cjp2.background = Color.BLUE
        cjp3.background = Color.GREEN
    // endregion

    cb2.isSelected = true

    // region layout
        layout = GroupLayout(this).apply {
            linkSize(XMin, xMin)
            linkSize(XMax, xMax)
            linkSize(YMin, yMin)
            linkSize(YMax, yMax)
            linkSize(cjp1, cjp2, cjp3, cb1, cb2, cb3)


            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(10)
                    .addGroup(createParallelGroup().addComponent(XMin).addComponent(YMin))
                    .addGroup(
                        createParallelGroup().addComponent(
                            xMin,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE
                        ).addComponent(
                            yMin,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addGap(10, 20, 20)
                    .addGroup(createParallelGroup().addComponent(XMax).addComponent(YMax))
                    .addGroup(
                        createParallelGroup().addComponent(
                            xMax,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE
                        ).addComponent(
                            yMax,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE,
                            GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addGap(50)
                    .addGroup(createParallelGroup().addComponent(cb1).addComponent(cb2).addComponent(cb3))
                    .addGroup(createParallelGroup().addComponent(set1).addComponent(set2).addComponent(set3))
                    .addGap(10)
                    .addGroup(createParallelGroup().addComponent(cjp1).addComponent(cjp2).addComponent(cjp3))
            )
            setVerticalGroup(
                createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(
                    createSequentialGroup().addGroup(
                        createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(XMin)
                            .addComponent(xMin)
                            .addComponent(XMax)
                            .addComponent(xMax)
                    )
                        .addGap(2)
                        .addGroup(
                            createParallelGroup()
                                .addComponent(YMin)
                                .addComponent(
                                    yMin,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                                .addComponent(YMax)
                                .addComponent(
                                    yMax,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                ).addGroup(
                    createSequentialGroup()
                        .addGroup(
                            createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(cb1)
                                .addComponent(set1)
                                .addComponent(cjp1)
                        )
                        .addGap(2)
                        .addGroup(
                            createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(cb2)
                                .addComponent(set2)
                                .addComponent(cjp2)
                        )
                        .addGap(2)
                        .addGroup(
                            createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(cb3)
                                .addComponent(set3)
                                .addComponent(cjp3)
                        )
                )
            )
        }
    // endregion
    }
}