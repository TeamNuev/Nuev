package ga.nullcraft.global.resource;

/**
 * IResourceLoader에서는 필요한 리소스들을 로드 합니다.
 * 병렬로 처리해 로드 할수도 있으며, 로드시 초기화 과정도
 * 거치게 됩니다. 주로 IStorage와 같이 사용 합니다.
 *
 * @author      storycraft
 */
public interface IResourceLoader<T> {
}
