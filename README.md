WorldPlayerManager
==================

Плагин для открытия/закрытия миров, телепортирования игроков из мира в мир.

<b>Команды:</b>
<ul>
<li><code>/wpm list</code> - выводит список миров, показывает какие миры открыты или закрыты</li>
<li><code>/wpm tp [worldFrom] [worldTo]</code> - телепортирует игроков из мира в другой мир (те игроки, которые имеют права <code>WorldPlayerManager.admin</code> не телепортируются)</li>
<li><code>/wpm open [world]</code> - открывает мир</li>
<li><code>/wpm close [world]</code> - закрывает мир (в этот мир могут входить только игроки с правами <code>WorldPlayerManager.admin</code>)</li>
</ul>

<b>Права:</b>
<code>WorldPlayerManager.admin</code> - права на использование команды <b>wpm</b>.

<b>Конфиг:</b>
<pre>joinafterclose: world # Мир по умолчанию, сюда будут поподать игроки, которые входят в игру в закрытый мир
closedworlds: [] # закрытые миры, используется плагином в основном для сохранения состояния миров</pre>

<b>Требования:</b> PluginManager, Vault
