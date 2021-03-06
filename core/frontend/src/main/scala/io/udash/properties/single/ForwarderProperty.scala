package io.udash.properties.single
import java.util.UUID

import io.udash.properties.{PropertyCreator, ValidationResult}

import scala.concurrent.{ExecutionContext, Future}

trait ForwarderReadableProperty[A] extends ReadableProperty[A] {
  protected def origin: ReadableProperty[_]

  override val id: UUID = PropertyCreator.newID()
  override protected[properties] def parent: ReadableProperty[_] = null

  override def isValid: Future[ValidationResult] =
    origin.isValid

  override def validate(): Unit =
    origin.validate()

  override protected[properties] def valueChanged(): Unit =
    origin.valueChanged()

  override implicit protected[properties] def executionContext: ExecutionContext =
    origin.executionContext
}

trait ForwarderProperty[A] extends ForwarderReadableProperty[A] with Property[A] {
  protected def origin: Property[_]
}
