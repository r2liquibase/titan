/*
 * Copyright (c) 2019 by Delphix. All rights reserved.
 */

package io.titandata.titan.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.arguments.argument
import io.titandata.titan.Dependencies
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.provider

class Start : CliktCommand(help = "Start a container for a repository") {
    private val dependencies: Dependencies by requireObject()
    private val repository by argument()
    override fun run() {
        val (provider, repoName) = dependencies.providers.byRepository(repository)
        provider.start(repoName)
    }
}

val startModule = Kodein.Module("start") {
    bind<CliktCommand>().inSet() with provider {
        Start()
    }
}
