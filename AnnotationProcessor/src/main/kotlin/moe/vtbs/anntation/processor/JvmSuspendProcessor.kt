/*
 * Copyright (C) 2022 一七年夏
 * 
 * The part of program is free: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published 
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/
 */
package moe.vtbs.anntation.processor

import com.google.auto.service.AutoService
import moe.vtbs.anntation.JvmSuspend
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 *  Jvm异步处理器
 *
 * @author 一七年夏
 * @since 2022-07-05 10:34
 */
@AutoService(Processor::class)
@SupportedAnnotationTypes("moe.vtbs.anntation.JvmSuspend")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class JvmSuspendProcessor : AbstractProcessor() {

    val msg get() = processingEnv.messager

    fun info(str: String) {
        msg.printMessage(Diagnostic.Kind.NOTE, str)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        if (annotations == null || roundEnv == null) return false
        val elements = roundEnv.getElementsAnnotatedWith(JvmSuspend::class.java)
        info(elements.joinToString("\n"))
        elements

        return false
    }
}