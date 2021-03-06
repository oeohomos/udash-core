package io.udash.bootstrap

import org.scalajs.dom._

import scalatags.JsDom.all._

/** Adds selected classes to provided DOM element. */
abstract class ClassModifier(styles: BootstrapStyles.BootstrapClass*) extends Modifier {
  override def applyTo(t: Element): Unit =
    styles.foreach(_.addTo(t))
}
