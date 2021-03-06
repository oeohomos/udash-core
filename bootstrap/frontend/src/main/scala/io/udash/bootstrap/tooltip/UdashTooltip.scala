package io.udash.bootstrap
package tooltip

import io.udash.wrappers.jquery._
import org.scalajs.dom

import scala.language.postfixOps
import scala.scalajs.js

class UdashTooltip private(selector: UdashTooltip.UdashTooltipJQuery) extends Listenable[UdashTooltip, UdashTooltip.TooltipEvent] {
  /** Shows the tooltip. */
  def show(): Unit =
    selector.tooltip("show")

  /** Hides the tooltip. */
  def hide(): Unit =
    selector.tooltip("hide")

  /** Toggles tooltip visibility. */
  def toggle(): Unit =
    selector.tooltip("toggle")

  /** Destroys the tooltip. */
  def destroy(): Unit =
    selector.tooltip("destroy")

  import UdashTooltip._
  selector.on("show.bs.tooltip", jQFire(TooltipShowEvent(this)))
  selector.on("shown.bs.tooltip", jQFire(TooltipShownEvent(this)))
  selector.on("hide.bs.tooltip", jQFire(TooltipHideEvent(this)))
  selector.on("hidden.bs.tooltip", jQFire(TooltipHiddenEvent(this)))
  selector.on("inserted.bs.tooltip", jQFire(TooltipInsertedEvent(this)))
}

object UdashTooltip extends TooltipUtils[UdashTooltip] {
  override protected def initTooltip(options: js.Dictionary[Any])(el: dom.Node): UdashTooltip = {
    val tp: UdashTooltipJQuery = jQ(el).asTooltip()
    tp.tooltip(options)
    new UdashTooltip(tp)
  }

  override protected val defaultPlacement: (dom.Element, dom.Element) => Seq[Placement] = (_, _) => Seq(TopPlacement)
  override protected val defaultTemplate: String = {
    import scalatags.Text.all._
    div(cls := BootstrapStyles.Tooltip.tooltip.cls, role := "tooltip")(
      div(cls := BootstrapStyles.Tooltip.tooltipArrow.cls),
      div(cls := BootstrapStyles.Tooltip.tooltipInner.cls)
    ).render
  }
  override protected val defaultTrigger: Seq[Trigger] = Seq(HoverTrigger, FocusTrigger)

  @js.native
  private trait UdashTooltipJQuery extends JQuery {
    def tooltip(arg: js.Any): UdashTooltipJQuery = js.native
  }

  private implicit class JQueryTooltipExt(jQ: JQuery) {
    def asTooltip(): UdashTooltipJQuery =
      jQ.asInstanceOf[UdashTooltipJQuery]
  }
}
