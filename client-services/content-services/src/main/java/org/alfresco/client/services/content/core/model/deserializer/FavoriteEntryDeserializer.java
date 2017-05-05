/*
 *   Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 *   This file is part of Alfresco Java Client.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.alfresco.client.services.content.core.model.deserializer;

import static org.alfresco.client.services.common.constant.ApiConstant.ENTRY;

import java.lang.reflect.Type;

import org.alfresco.client.services.common.model.deserializer.EntryDeserializer;
import org.alfresco.client.services.content.core.model.representation.*;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Created by jpascal on 19/01/2016.
 */
public class FavoriteEntryDeserializer extends EntryDeserializer<FavoriteRepresentation>
{
    @Override
    public FavoriteRepresentation deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        JsonElement entry = je;
        if (je.getAsJsonObject().has(ENTRY))
        {
            entry = je.getAsJsonObject().get(ENTRY);
        }

        FavoriteRepresentation favorite = null;
        JsonObject target = entry.getAsJsonObject().get("target").getAsJsonObject();
        if (target.has("file"))
        {
            favorite = jdc.deserialize(entry, FavoriteFileRepresentation.class);
            NodeRepresentation file = jdc.deserialize(target.get("file").getAsJsonObject(), NodeRepresentation.class);
            favorite.setTarget(file);
        }
        else if (target.has("folder"))
        {
            favorite = jdc.deserialize(entry, FavoriteFolderRepresentation.class);
            NodeRepresentation file = jdc.deserialize(target.get("folder").getAsJsonObject(), NodeRepresentation.class);
            favorite.setTarget(file);
        }
        else if (target.has("site"))
        {
            favorite = jdc.deserialize(entry, FavoriteSiteRepresentation.class);
            SiteRepresentation site = jdc.deserialize(target.get("site").getAsJsonObject(), SiteRepresentation.class);
            favorite.setTarget(site);
        }

        return favorite;
    }
}
