<#macro footer>
    <style>#page-container {
            position: relative;
            min-height: 90vh;
            padding-top: 60px;
        }

        #footer {
            background: darkgray;
            color: white;
            text-align: center;
            position: absolute;
            bottom: -10vh;
            left: 0;
            width: 100%;
            height: 2.5rem; /* Footer height */
        }</style>

    <div id="page-container">
        <#nested>
        <footer id="footer" class="mb-0">Copyright © 2020 meny.by by Spy Girls</footer>
    </div>

</#macro>