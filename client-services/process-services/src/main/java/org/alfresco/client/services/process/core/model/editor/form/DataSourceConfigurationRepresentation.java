/*
 *  Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 *  This file is part of Alfresco Activiti Client.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.alfresco.client.services.process.core.model.editor.form;

import java.util.List;

/**
 * @author Tijs Rademakers
 */
public class DataSourceConfigurationRepresentation
{

    protected Long dataModelId;

    protected String entity;

    protected List<QueryParameterConfigurationRepresentation> queryParameters;

    public DataSourceConfigurationRepresentation()
    {

    }

    public DataSourceConfigurationRepresentation(Long dataModelId, String entity)
    {
        this.dataModelId = dataModelId;
        this.entity = entity;
    }

    public Long getDataModelId()
    {
        return dataModelId;
    }

    public void setDataModelId(Long dataModelId)
    {
        this.dataModelId = dataModelId;
    }

    public String getEntity()
    {
        return entity;
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public List<QueryParameterConfigurationRepresentation> getQueryParameters()
    {
        return queryParameters;
    }

    public void setQueryParameters(List<QueryParameterConfigurationRepresentation> queryParameters)
    {
        this.queryParameters = queryParameters;
    }
}
