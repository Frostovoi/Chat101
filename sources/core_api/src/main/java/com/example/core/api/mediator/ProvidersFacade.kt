package com.example.core.api.mediator

import com.example.core.api.database.DatabaseProvider

interface ProvidersFacade : AppProvider, DatabaseProvider, MediatorsProvider