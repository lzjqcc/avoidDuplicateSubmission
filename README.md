# avoidDuplicateSubmission
##防重复提交的本意

是当网络出现延迟的时候，用户多次点击按钮提交后，防止产生多条相同数据。
##实现

这个简单的防重复提交的demo是居于限制请求接口实现的，比如用户访问`/user/commit`接口的时候，如果出现网络延迟或者程序执行缓慢导致第一个请求还在处理中，这个时候将在`preHandler`中将`/user/commit`作为key放入`session`中。第二个请求过来的时候会判断`session`中是否存在`/user/commit`，如果存在，则表示第一个请求还没有处理完成，因此会拒绝接受第二个请求。
