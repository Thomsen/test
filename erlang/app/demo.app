{application, demo,
[
{description, "Demo application"},
{vsn, "0.0.1"},
{modules, [demo_app, demo_sup]},
{registered, [demo_app]},
{mod, {demo_app, []}},
{env, []},
{applications, [kernel, stdlib]}
]
}.
