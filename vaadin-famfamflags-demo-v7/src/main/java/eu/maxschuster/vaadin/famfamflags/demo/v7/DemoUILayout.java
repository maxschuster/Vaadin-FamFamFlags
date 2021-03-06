/*
 * Copyright 2016 Max Schuster.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.maxschuster.vaadin.famfamflags.demo.v7;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/**
 *
 * @author Max Schuster
 */
@DesignRoot
class DemoUILayout extends VerticalLayout {
    
    Table availableLocales;
    Panel panel;
    
    DemoUILayout() {
        Design.read(this);
    }
    
}
